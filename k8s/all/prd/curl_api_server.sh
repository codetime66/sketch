#!/bin/bash
APISERVER=$(kubectl config view --minify -o jsonpath='{.clusters[0].cluster.server}')
echo "apiserver=$APISERVER"
TOKEN=$(kubectl get secret $(kubectl get serviceaccount default -o jsonpath='{.secrets[0].name}') -o jsonpath='{.data.token}' | base64 --decode )
echo "token=$TOKEN"
#curl $APISERVER/api --header "Authorization: Bearer $TOKEN" --insecure

curl $APISERVER/api/v1/nodes/hquantum01.qa.stelo.intranet/proxy/metrics/cadvisor --header "Authorization: Bearer $TOKEN" --insecure
