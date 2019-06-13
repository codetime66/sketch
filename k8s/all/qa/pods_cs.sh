#!/bin/bash
pods=$(kubectl get pods --selector=job-name=pi --namespace=jobs --output=jsonpath='{.items[*].metadata.name}')
echo $pods
