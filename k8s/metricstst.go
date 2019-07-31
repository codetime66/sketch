package main

import (
    "flag"
    "path/filepath"
    "k8s.io/client-go/tools/clientcmd"
    "os"
    "encoding/json"
    "fmt"
    "time"

    "k8s.io/client-go/kubernetes"
)

// PodMetricsList : PodMetricsList
type PodMetricsList struct {
    Kind       string `json:"kind"`
    APIVersion string `json:"apiVersion"`
    Metadata   struct {
        SelfLink string `json:"selfLink"`
    } `json:"metadata"`
    Items []struct {
        Metadata struct {
            Name              string    `json:"name"`
            Namespace         string    `json:"namespace"`
            SelfLink          string    `json:"selfLink"`
            CreationTimestamp time.Time `json:"creationTimestamp"`
        } `json:"metadata"`
        Timestamp  time.Time `json:"timestamp"`
        Window     string    `json:"window"`
        Containers []struct {
            Name  string `json:"name"`
            Usage struct {
                CPU    string `json:"cpu"`
                Memory string `json:"memory"`
            } `json:"usage"`
        } `json:"containers"`
    } `json:"items"`
}

func getMetrics(clientset *kubernetes.Clientset, pods *PodMetricsList) error {
    data, err := clientset.RESTClient().Get().AbsPath("apis/metrics.k8s.io/v1beta1/pods").DoRaw()
    if err != nil {
        return err
    }
    err = json.Unmarshal(data, &pods)
    return err
}

func main() {
    var kubeconfig *string
    if home := homeDir(); home != "" {
		kubeconfig = flag.String("kubeconfig", filepath.Join(home, ".kube", "config"), "(optional) absolute path to the kubeconfig file")
    } else {
		kubeconfig = flag.String("kubeconfig", "", "absolute path to the kubeconfig file")
    }
    flag.Parse()

    // use the current context in kubeconfig
    config, err := clientcmd.BuildConfigFromFlags("", *kubeconfig)
    if err != nil {
	panic(err.Error())
    }
    // creates the clientset
    clientset, err := kubernetes.NewForConfig(config)
    if err != nil {
        panic(err.Error())
    }
    var pods PodMetricsList
    err = getMetrics(clientset, &pods)
    if err != nil {
        panic(err.Error())
    }
    for _, m := range pods.Items {
        fmt.Println(m.Metadata.Name, m.Metadata.Namespace, m.Timestamp.String(), m.Containers)
    }
}

func homeDir() string {
	if h := os.Getenv("HOME"); h != "" {
		return h
	}
	return os.Getenv("USERPROFILE") // windows
}
