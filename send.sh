#!/bin/bash
curl --header "Content-Type: application/json" --request POST --data '{
   "version":"1",
   "groupKey":"2",
   "status":"3",
   "receiver":"4",
   "externalURL":"8",
   "alerts":[
      {
         "status":"status1",
	 "labels": {"alertname": "label1"},
	 "annotations": {"annotation": "annotation1"},
	 "startsAt":"1",
         "endsAt":"2",
         "generatorURL":"url1"
      },
      {
         "status":"status2",
	 "labels": {"alertname": "label2"},
	 "annotations": {"annotation": "annotation1"},
         "startsAt":"3",
         "endsAt":"4",
         "generatorURL":"url2"
      }
   ]
}' http://127.0.0.1:8080/alerts

