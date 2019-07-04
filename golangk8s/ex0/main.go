/*
Copyright 2016 The Kubernetes Authors.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/

// Note: the example only works with the code within the same release/branch.
package main

import (
	"flag"
	"fmt"
	"os"
	"path/filepath"
	"time"

	"k8s.io/apimachinery/pkg/api/errors"
	metav1 "k8s.io/apimachinery/pkg/apis/meta/v1"
	"k8s.io/client-go/kubernetes"
	"k8s.io/client-go/tools/clientcmd"
	//
	// Uncomment to load all auth plugins
	// _ "k8s.io/client-go/plugin/pkg/client/auth"
	//
	// Or uncomment to load specific auth plugins
	// _ "k8s.io/client-go/plugin/pkg/client/auth/azure"
	// _ "k8s.io/client-go/plugin/pkg/client/auth/gcp"
	// _ "k8s.io/client-go/plugin/pkg/client/auth/oidc"
	// _ "k8s.io/client-go/plugin/pkg/client/auth/openstack"
)

func main() {

	tempconf:= `
	apiVersion: v1
	clusters:
	- cluster:
			insecure-skip-tls-verify: true
			server: https://localhost:6443
		name: docker-for-desktop-cluster
	contexts:
	- context:
			cluster: docker-for-desktop-cluster
			user: docker-for-desktop
		name: docker-for-desktop
	current-context: docker-for-desktop
	kind: Config
	preferences: {}
	users:
	- name: docker-for-desktop
		user:
			client-certificate-data: LS0tLS1CRUdJTiBDRVJUSUZJQ0FURS0tLS0tCk1JSUM5RENDQWR5Z0F3SUJBZ0lJWTFnZjBXS085and3RFFZSktvWklodmNOQVFFTEJRQXdGVEVUTUJFR0ExVUUKQXhNS2EzVmlaWEp1WlhSbGN6QWVGdzB4T0RBNE1UY3hOak0yTVRaYUZ3MHlNREEyTURReU16TTNORE5hTURZeApGekFWQmdOVkJBb1REbk41YzNSbGJUcHRZWE4wWlhKek1Sc3dHUVlEVlFRREV4SmtiMk5yWlhJdFptOXlMV1JsCmMydDBiM0F3Z2dFaU1BMEdDU3FHU0liM0RRRUJBUVVBQTRJQkR3QXdnZ0VLQW9JQkFRQ2RMd2pvZXJHZXBCY1cKaE5RUVVJUUFIRXliL0xSenJ6L2l6MEtSRlM5dUFUQjVTTGxPSTNHcU1qamhReitVeHRneGU0blZTREtwdU4vUQowWlVzTk1ZandGMVRRTnV1RlgzUDNQdVJyc3d6V0wxSTNkOHlGQXkwekxEb3N0RFljRy9sQWtCSGJJcVF4R1ZuCnJhNlF5SlFTdmI3VmFtcFFZb2VuVFkzckhPVVpzTkNxQnRsaDhUSkkxVXdlS2RYdUxSZ3VkSU4zRllNdDhKQUgKVzliNW1SdTFlZHFzRzVVSms1eWhJOURETm5YMkJiYjc5T1RibmxmUTNFTy81UCsvVVhmL1ZMTko0eHJOOEsrdwpVQXIzNCtNWjlydzk1VWVmRVZ1ZGk5V2UycmpRUXdRMDZsenY5NUJuT09Eek9JbVZUcUNTeUx1c3VrYUJHN0szCmdMSnZqaXVEQWdNQkFBR2pKekFsTUE0R0ExVWREd0VCL3dRRUF3SUZvREFUQmdOVkhTVUVEREFLQmdnckJnRUYKQlFjREFqQU5CZ2txaGtpRzl3MEJBUXNGQUFPQ0FRRUFZcHhGQkpUajVhNldacWROeWw0c3RVSlVyQnZsY0laMApqU1dVZ1hXZVlzMkd6R3V5VWVTd3JzRnlJQ0gxelZ0TEptajhYcnpFQ0FCZnRkOW5Lclpsd1hjZFBOckxqMWRYClNNektBQjJSWDAzUk15bmFweWU3QUpxMXJhNGZrU29ycHRqa3NYU3NhMUlLdGJVWHBkdTJqSDNkUlIwK2RKTmwKU1o1OHNIV2d0MENlQm12TWlLY1Zmb1drV0VlaFdtLzZJb1o0Ukt4bDc4bHZmUTFQSzl6OUl5OVBxSkJjSHJhNApKSTZ2RjM4dHNLVk9TTFFPNmFubFJyd2lzLzY1V0hBMDhMcENMUi9sWGU0elp6cS9FS1BCR3pScUxMWXZBZXdHCnBDZnIvUnl0blV0R0dlUlZNMjhmZ1VoZ1cwZU1RbzhpditONFBQMUFSUksvOVNKVkZXTDN6dz09Ci0tLS0tRU5EIENFUlRJRklDQVRFLS0tLS0K
			client-key-data: LS0tLS1CRUdJTiBSU0EgUFJJVkFURSBLRVktLS0tLQpNSUlFb3dJQkFBS0NBUUVBblM4STZIcXhucVFYRm9UVUVGQ0VBQnhNbS95MGM2OC80czlDa1JVdmJnRXdlVWk1ClRpTnhxakk0NFVNL2xNYllNWHVKMVVneXFiamYwTkdWTERUR0k4QmRVMERicmhWOXo5ejdrYTdNTTFpOVNOM2YKTWhRTXRNeXc2TExRMkhCdjVRSkFSMnlLa01SbFo2MnVrTWlVRXIyKzFXcHFVR0tIcDAyTjZ4emxHYkRRcWdiWgpZZkV5U05WTUhpblY3aTBZTG5TRGR4V0RMZkNRQjF2Vytaa2J0WG5hckJ1VkNaT2NvU1BRd3paMTlnVzIrL1RrCjI1NVgwTnhEditUL3YxRjMvMVN6U2VNYXpmQ3ZzRkFLOStQakdmYThQZVZIbnhGYm5ZdlZudHE0MEVNRU5PcGMKNy9lUVp6amc4emlKbFU2Z2tzaTdyTHBHZ1J1eXQ0Q3liNDRyZ3dJREFRQUJBb0lCQUUzKzhPU3JQVWdERkpIUQpCNHF3NlU5RXU1OExubDlwTHNKVkppcm40RWt0dmVmdzlrS1h3K2M0VkpFeTFYSDEyWUYwVThqSnNwb1Zhcm9yCnhXSFNMaCsvYkkvTE56Y0M0eE1xRElzVnVhNGc4b3dkeklzRTkra2ZTUW11OFlMMUNCRVROT0k4TWVYMERuMkwKNlh0ZWpGdGdYRGw2UEpiMnpXbHZTbVRJU2NhYzkxSXRoMklWTFlNQ2JjT0ZoQzBUSHRweFR5SlZBZ0xPa2I1SwovSlZJeU8zblJSMkgxaElDc29MSFpieVdXZG00VENNZ3RaNDJkSmRNc2c3cFFGcytZVXpyN3FlRGtSdjZ0MzdlCnRsZU9mVGRUTUp3eFJzS3loeVZYL2s2a25DZFpYQ3M2N0JCeFBlcHhYZUs4a05KdUw1SDlNSTEzL2tjQzVWdmoKSy9Vc0NrRUNnWUVBeFNmdlFpZE9DNUNvSFFqczhjQUFMT24wNkpFYUVnc3o0RW1LL1BGdGNTTyt4TENZZXExSgp1MUI4Q1MxQ0VNYytKNGNsajhMZDM4L295cE9SeTBpSXAxb2lwVjl1ZGRZVkxFdTVYaWR1amFJWi82NnJqNDFKCkNmQ2lGSEVHa1dhMXhwVFNtcDYrajQ2ajBXMklSNmorbG5ZWk4ydE5MT25PYmRUQlRuczdCWjBDZ1lFQXpCankKSzVya2hqREc5MzJnbGZGb2x3WW1leEVHamZyK2hqT2JuU0dpUSsxOGRwMGlWNjlwWlZWOVZFNmlDTHVEOExwcwpoQ1lTSUp3ZGgvRzdEeVY5MWttRitWdTdyVmtBRTBnelFjU0VpV0RQUXBRZmJ6eTQ4REJLQkdMRTVEK0F2bUJlCkhBaFJJdU1yQVdJV1lLbmhUNUxyRW8vVDZVdTRlOSs0ZHRnOHU1OENnWUVBajBySENqMm02NkRuanJLK0E3YVkKYWd4Z2crZHlHMHNwR1FwYWloeXVmbHlNZWtnZmlvb0gzS3hTZWhqNnUxY1VSWVprTm1oYStpQVNBYW5mZk8vcQo0amNRbDBFVTkyL3hVNUIxMllpdlFTRmY1QVQvakFZVDM3V1ZGMlpPYjc5R3ZsM0w4N1VNcXlud3huWGxacS9jCjJWNWNWWHNYVEYxaU8xRTJiZVFDc2NFQ2dZQUgvQ0hkV3QzOThnS1d4Qzk0TGsydE91eGVmRGdKYmZ4dk82Mm0KNUIzNUVsMkFObjJWZUtOaTk0bER0eC9YdjU1VjJMVk9KajhuVDNSSVVVakVPdTlGWnZMNjRuL2hORTR3Y3U2dQpjQVhQYWg0eHVLSFNORW4zKzZ1cHFFT1R1bmhLK25JMEhvOHVFajBvZkxIcmJqb0dadlVUY05VVmw0VE1NK0IvCnBjNGFlUUtCZ0hKYzl1em8yQ1JIWG02Rm5XUU9RdXAvQ1dUUGFZYlMxQ3B5NXpRTy9oamhlSHNoRDBEektSVTAKeUIzWldhckJ1SkEwL0VrbkoyV0pXTEhoK0loMU9CRmJqdVVjanFDU2VURDRnblJqS1JpRGF1KzM1Mkhzcm4reApuZENvYzhYUEZmTjZmaG52Uk9paVBOMCtkOTIzZU9wd2NPQ1VCejAwM1ZxdW8vaVhoN0pzCi0tLS0tRU5EIFJTQSBQUklWQVRFIEtFWS0tLS0tCg==
	`
  fmt.Printf(tempconf) 

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

	// create the clientset
	clientset, err := kubernetes.NewForConfig(config)
	
	if err != nil {
		panic(err.Error())
	}
	for {
		pods, err := clientset.CoreV1().Pods("").List(metav1.ListOptions{})
		if err != nil {
			panic(err.Error())
		}
		fmt.Printf("There are %d pods in the cluster\n", len(pods.Items))

		// Examples for error handling:
		// - Use helper functions like e.g. errors.IsNotFound()
		// - And/or cast to StatusError and use its properties like e.g. ErrStatus.Message
		namespace := "default"
		pod := "example-xxxxx"
		_, err = clientset.CoreV1().Pods(namespace).Get(pod, metav1.GetOptions{})
		if errors.IsNotFound(err) {
			fmt.Printf("Pod %s in namespace %s not found\n", pod, namespace)
		} else if statusError, isStatus := err.(*errors.StatusError); isStatus {
			fmt.Printf("Error getting pod %s in namespace %s: %v\n",
				pod, namespace, statusError.ErrStatus.Message)
		} else if err != nil {
			panic(err.Error())
		} else {
			fmt.Printf("Found pod %s in namespace %s\n", pod, namespace)
		}

		time.Sleep(10 * time.Second)
	}
}

func homeDir() string {
	if h := os.Getenv("HOME"); h != "" {
		return h
	}
	return os.Getenv("USERPROFILE") // windows
}
