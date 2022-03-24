docker_build:
	docker build -t aulera/bookclass:v1.0.0 .
	docker push aulera/bookclass:v1.0.0
