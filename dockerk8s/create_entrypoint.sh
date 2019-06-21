#!/bin/bash
cat << EOF > app-entrypoint.sh
#!/bin/sh
# Created on $(date)
java -Xmx256m -Xms64m -jar /app/service/bin/${APP_PKG_NAME} --spring.config.name=$(echo "$APP_CFG_NAME" | cut -f 1 -d '.') --spring.config.location=file:/app/service/config/
EOF
