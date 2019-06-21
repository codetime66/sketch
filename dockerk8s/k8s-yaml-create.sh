#!/bin/bash
source ./k8s-config.sh
#
cat << EOF >${K8S_YAML_NAME}
apiVersion: v1
kind: Service
metadata:
  name: ${K8S_APP_NAME}
  namespace: ${K8S_NAMESPACE} 
  labels:
    app: ${K8S_APP_NAME}
spec:
  type: NodePort
  ports:
    - port: ${k8S_PORT}
      protocol: TCP
      targetPort: ${k8S_PORT}
      nodePort: ${K8S_NODEPORT}
  selector:
    app: ${K8S_APP_NAME}
---
apiVersion: apps/v1 
kind: Deployment
metadata:
  namespace: ${K8S_NAMESPACE} 
  name: ${K8S_APP_NAME}
spec:
  replicas: 1
  selector:
    matchLabels:
      app: ${K8S_APP_NAME}
  template:
    metadata:
      labels:
        app: ${K8S_APP_NAME}
    spec:
      containers:
      - name: ${K8S_APP_NAME}
        image: ${K8S_IMAGE_NAME}
        imagePullPolicy: Always
        ports:
        - containerPort: ${k8S_PORT}
          protocol: TCP
        volumeMounts:
        - name: silocarquivos
          mountPath: /opt/silocarquivos/
      volumes:
      - name: silocarquivos
        hostPath:
          path: /opt/silocarquivos/

EOF
