package main

import (
  "fmt"

  "k8s.io/api/extensions/v1beta1"
  "k8s.io/client-go/kubernetes/scheme"
)

var json = `
{
  "apiVersion": "extensions/v1beta1",
  "kind": "Deployment",
  "metadata": null,
  "name": "my-nginx",
  "replicas": 2,
  "spec": null,
  "template": {
    "metadata": {
      "labels": {
        "run": "my-nginx"
      }
    },
    "spec": {
      "containers": [
        {
          "image": "nginx",
          "name": "my-nginx",
          "ports": [
            {
              "containerPort": 80
            }
          ]
        }
      ]
    }
  }
}
`

func main() {
	decode := scheme.Codecs.UniversalDeserializer().Decode

	obj, _, err := decode([]byte(json), nil, nil)
	if err != nil {
		fmt.Printf("%#v", err)
	}

	deployment := obj.(*v1beta1.Deployment)

	fmt.Printf("%#v\n", deployment)
}
