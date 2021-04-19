#!/bin/bash

#start docker daemon if it is not running.
sudo systemctl status docker || systemctl start docker

# pull postgres image
docker pull postgres

# save cmd line arguments in variables
cli_arg1=$1
db_username=$2
db_password=$3

# case statements to execute code based on CLI argument values
# case when arg1 is create
case "$cli_arg1" in
  "create")

  # exit code if db_username and db_password are not provided as CLI args
  if  [ "$db_username" == "" ] || [ "$db_password" == "" ] ; then
        echo "Did not enter username or password"
        exit 1
  fi

  # validate command line arguments, exit code if CLI args are not equal to 3
  if [ "$#" -ne 3 ]; then
    echo "Illegal number of parameters"
    exit 1
  fi

  # check if docker container jrvs-psql is already created
  cmd_success_check_1='docker container ls -a -f name=jrvs-psql | wc -l';
  if [ "$cmd_success_check_1" -eq 2 ]; then
      echo "Container jrvs-psql is already created"
      exit 1
  fi

  # create new volume
  docker volume create pgdata

  # create a container using psql image with name=jrvs-psql
  docker run --name jrvs-psql -e POSTGRES_PASSWORD="$db_password" -e POSTGRES_USER="$db_username" -d -v pgdata:/var/lib/postgresql/data -p 5432:5432 postgres
  exit $?
  ;;

  "start")
    # validate cmd line arguments, exit code if CLI args > 1
    if [ "$#" -ne 1 ]; then
      echo "Illegal number of parameters"
      exit 1
    fi

    # check if docker container jrvs-psql is already created
    cmd_success_check_2='docker container ls -a -f name=jrvs-psql | wc -l';
    if [ "$cmd_success_check_2" -ne 2 ]; then
      echo "Container jrvs-pql is not created"
      exit 1
    fi
    docker container start jrvs-psql
    echo "Container jrvs-psql started successfully."
    ;;

  "stop")
    # validate cmd line arguments, exit code if CLI args > 1
    if [ "$#" -ne 1 ]; then
      echo "Illegal number of parameters"
      exit 1
    fi

    # check if docker container jrvs-psql is already created
    cmd_success_check_3='docker container ls -a -f name=jrvs-psql | wc -l';
    if [ "$cmd_success_check_3" -ne 2 ]; then
      echo "Container jrvs-pql is not created"
      exit 1
    fi
    docker container stop jrvs-psql
    echo "Container jrvs-psql stopped successfully."
    ;;
  *)
    echo "Invalid command line argument provided."
esac

exit 0






