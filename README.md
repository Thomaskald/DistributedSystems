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

Change it from "server: https://127.0.0.1:16443" to "server: https://<server-name-ip>:16443"
Delete "certificate-authority-data" and add "insecure-skip-tls-verify: true" in its place.

Go to project folder of the application and:

	export KUBECONFIG=~/.kube/devops-microk8s
	k apply -f k8s/postgres/postgres-pvc.yaml
	k apply -f k8s/postgres/postgres-deployment.yaml
	k apply -f k8s/postgres/postgres-svc.yaml
	
	k apply -f k8s/spring/spring-deployment.yaml
	k apply -f k8s/spring/spring-svc.yaml
	
	k get po
	k port-forward <spring-deployment-name> 7000:8080

	Open browser on localhost:7000
