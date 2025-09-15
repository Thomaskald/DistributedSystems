Kubernetes instructions:

Create a vm and install microk8s on it:

	snap install microk8s --classic
	
	sudo ufw allow in on ethh0 && sudo ufw allow out on eth0
	sudo ufw default allow routed



Access the microk8s without sudo:

	sudo usermod -a -G microk8s $USER
	exit
	ssh <vm-name>

Enable dns, ingress, storage:

	microk8s enable dns ingress storage

Open inbound port 16443 from your vm.

Then:

	microk8s.kubectl config view --raw > $HOME/.kube/config

From your pc now:

	scp <vm-name>:~/.kube/config ~/.kube/devops-microk8s
	vim ~/.kube/devops-microk8s

Change it from "server: https://127.0.0.1:16443" to "server: https://your-server-name-ip:16443"
Delete "certificate-authority-data" and add "insecure-skip-tls-verify: true" in its place.

Go to project folder of the application and:

	export KUBECONFIG=~/.kube/devops-microk8s
	kubectl apply -f k8s/postgres/postgres-pvc.yaml
	kubectl apply -f k8s/postgres/postgres-deployment.yaml
	kubectl apply -f k8s/postgres/postgres-svc.yaml
	
	kubectl apply -f k8s/spring/spring-deployment.yaml
    kubectl apply -f k8s/spring/spring-ingress-ssl.yaml
	kubectl apply -f k8s/spring/spring-svc.yaml
    
    kubectl apply -f k8s/cluster/cluster-issuer.yaml


Add helm and cert related dependencies, open ports 80,443 and a public https site is created. In my case my app is open on:
    
	https://spring.fotis.cloudns.pro/

Jenkins instructions:
	
 Create the vm:
  
 	docker run -d   --name jenkins   -p 8080:8080 -p 50000:50000   -v jenkins_home:/var/jenkins_home   -v /var/run/docker.sock:/var/run/docker.sock   jenkins/jenkins:lts
 
 Connect to the vm:
 	
  	docker exec -u root -it jenkins bash

 Install docker.io:
 
   	apt-get update
	apt-get install -y docker.io
	exit
 
 Restart jenkins:
 	
  	docker restart jenkins

 Get the permissions necessary:
 
	sudo groupadd docker
	sudo usermod -aG docker jenkins 
	sudo chmod 666 /var/run/docker.sock
 
 Now you open locahost:8080 on your pc and from there u create a pipeline that executes Jenkinsfile. Hit localhost:8081 on your browser and the project is running.
