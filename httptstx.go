package main

import (
	"bytes"
	"fmt"
	"io/ioutil"
	"net/http"
)

func main() {

	body := "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:eds=\"http://eds.wsdl.cms.verifone.com/\"><soapenv:Header/><soapenv:Body><eds:getMerchantInfo><id_acquirer>15</id_acquirer><merchant_number>163779</merchant_number><branch_number>0</branch_number><language>PT</language></eds:getMerchantInfo></soapenv:Body></soapenv:Envelope>"

	client := &http.Client{}
	req, err := http.NewRequest("POST", "http://wshp09a.hml1.stelo.local/CMS-STD-WEB-SERVICES-ACQUIRER/EDSws", bytes.NewBuffer([]byte(body)))
	if err != nil {
		fmt.Println(err)
	}

	req.Header.Set("username", "PORTSTELO")
	req.Header.Set("password", "1::3eb52c706e8b842fb275b6737d1c7eb4d8da401360c1ce87f7671b82e5f2a9c9")
	req.Header.Set("encrypted", "TRUE")
	req.Header.Set("Content-type", "text/xml")
	/*	req.Header.Set("SOAPAction", "eds:getMerchantInfo")
	 */
	resp, err := client.Do(req)
	if err != nil {
		fmt.Println(err)
	}
	/*	fmt.Println(resp.Body)
	 */
	defer resp.Body.Close()

	if resp.StatusCode == http.StatusOK {
		bodyBytes, err2 := ioutil.ReadAll(resp.Body)
		if err2 != nil {
			fmt.Println(err2)
		}
		bodyString := string(bodyBytes)
		fmt.Println(bodyString)
	}
}
