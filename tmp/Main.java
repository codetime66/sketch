package stelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import stelo.accreditation.LinkTerminalSellerReceiver;
import stelo.util.LoggingHttpRequestInterceptor;
import stelo.util.SteloLogger;

@SpringBootApplication(scanBasePackages = {"stelo"})
@EnableCaching
@EnableSwagger2
@EnableAsync
@PropertySource(value = "file://${applicationFilePath}", ignoreResourceNotFound = true)
public class Main {
  private static final SteloLogger LOG = new SteloLogger<Main>(Main.class);

  public static void main(String[] args) {
    stelo.util.MessageSender.appName = "link-terminal-seller-api";
    LOG.info("Iniciando serviço link-terminal-seller-api...");
    SpringApplication app = new SpringApplication(Main.class);
    app.setWebEnvironment(true);
    app.setWebApplicationType(WebApplicationType.SERVLET);
    app.run();
  }

  @Value("${stelo.proxy.url}")
  private String proxyUrl;

  @Value("${stelo.proxy.noproxy}")
  private String[] noproxy;

  @Value("${stelo.proxy.port}")
  private Integer proxyPort;

  @Value("${stelo.queue.name.link-terminal-seller}")
  private String queueName;

  @Value("${stelo.queue.name.link-terminal-seller-dlq}")
  private String queueNameDlq;

  @Value("${stelo.topic.exchange.name}")
  private String topicExchangeName;

  @Bean
  Queue queue() {
    return QueueBuilder.durable(queueName)
                .withArgument("x-dead-letter-exchange", "")
                .withArgument("x-dead-letter-routing-key", queueNameDlq)
                .build();
  }

  @Bean
  Queue deadLetterQueue() {
    return QueueBuilder.durable(queueNameDlq).build();
  }

  @Bean
  TopicExchange exchange() {
    return new TopicExchange(topicExchangeName);
  }

  @Bean
  Binding binding(Queue queue, TopicExchange exchange) {
    return BindingBuilder.bind(queue).to(exchange).with("stelo.link-terminal-seller.#");
  }

  @Bean
  SimpleMessageListenerContainer container(
      ConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {
    SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
    container.setConnectionFactory(connectionFactory);
    container.setQueueNames(queueName);
    container.setMessageListener(listenerAdapter);
    return container;
  }

  @Bean
  MessageListenerAdapter listenerAdapter(LinkTerminalSellerReceiver receiver) {
    return new MessageListenerAdapter(receiver, "receiveMessage");
  }

  @Bean
  public RestTemplate getRestTemplate(RestTemplateBuilder builder) {
    RestTemplate restTemplate =
        builder
            .additionalCustomizers(
                new RestTemplateCustomizer() {

                  @Override
                  public void customize(RestTemplate restTemplate) {
                    HttpHost proxy = new HttpHost(proxyUrl, proxyPort);
                    HttpClient httpClient =
                        HttpClientBuilder.create()
                            .setRoutePlanner(
                                new DefaultProxyRoutePlanner(proxy) {
                                  @Override
                                  protected HttpHost determineProxy(
                                      HttpHost target,
                                      org.apache.http.HttpRequest request,
                                      org.apache.http.protocol.HttpContext context)
                                      throws HttpException {
                                    HttpHost httpHost = null;
                                    LOG.debug(
                                        "Determinando proxy para rota {}", target.getHostName());
                                    boolean avoidProxy = false;
                                    for (String noProxyHost : noproxy) {
                                      if (target.getHostName().endsWith(noProxyHost)) {
                                        LOG.debug(
                                            "Rota interna, nao usa proxy para {} ",
                                            target.getHostName());
                                        avoidProxy = true;
                                        break;
                                      }
                                    }
                                    if (!avoidProxy) {
                                      LOG.debug("Rota externa, utilizando proxy {}", proxy);
                                      httpHost = super.determineProxy(target, request, context);
                                    }
                                    return httpHost;
                                  }
                                })
                            .build();
                    HttpComponentsClientHttpRequestFactory httpRequestFactory =
                        new HttpComponentsClientHttpRequestFactory(httpClient);
                    httpRequestFactory.setConnectionRequestTimeout(65000);
                    restTemplate.setRequestFactory(
                        new BufferingClientHttpRequestFactory(httpRequestFactory));
                  }
                })
            .build();

    List<ClientHttpRequestInterceptor> interceptors = new ArrayList<ClientHttpRequestInterceptor>();
    interceptors.add(new LoggingHttpRequestInterceptor());
    restTemplate.setInterceptors(interceptors);
    return restTemplate;
  }

  @CacheEvict(cacheNames = {"CacheModeloMaquinaListByStatus", "CacheModeloMaquina"})
  @Scheduled(fixedDelay = 10 * 60 * 1000, initialDelay = 500)
  public void flushCache() {
    LOG.info("Limpando caches.");
  }

  @Bean
  public Docket linkTerminalSellerApiLatest() {
    return new Docket(DocumentationType.SWAGGER_2)
        .groupName("LinkTerminalSellerAPI-Latest")
        .select()
        .paths(PathSelectors.regex("/latest.*"))
        .apis(RequestHandlerSelectors.basePackage("stelo"))
        .build()
        .apiInfo(
            new ApiInfoBuilder()
                .version("latest")
                .title("LinkgTerminalSeller API")
                .description("Documentation LinkgTerminalSeller API latest")
                .build());
  }

  @Bean
  public Docket linkTerminalSellerApiV10() {
    return new Docket(DocumentationType.SWAGGER_2)
        .groupName("LinkTerminalSellerAPI-v1")
        .select()
        .paths(PathSelectors.regex("/v1.0.*"))
        .apis(RequestHandlerSelectors.basePackage("stelo"))
        .build()
        .apiInfo(
            new ApiInfoBuilder()
                .version("v1.0")
                .title("LinkgTerminalSeller API")
                .description("Documentation LinkgTerminalSeller API V1.0")
                .build());
  }
}
