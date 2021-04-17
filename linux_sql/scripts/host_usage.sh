#!/bin/bash

psql_host=$1
psql_port=$2
db_name=$3
psql_user=$4
psql_password=$5

export PGPASSWORD=$psql_password

timestamp=$(vmstat -t | egrep -v 'timestamp|UTC' | awk '{ print $18" "$19 }' | xargs)
memory_free=$(vmstat --unit MB | egrep -v 'memory|free' | awk '{ print $4 }' | xargs)
cpu_idle=$(vmstat | egrep -v 'cpu|id' | awk '{ print $15 }' | xargs)
cpu_kernel=$(vmstat | egrep -v 'cpu|sy' | awk '{ print $14 }' | xargs)
disk_io=$(vmstat -d | egrep -v 'IO|cur' | awk '{ print $10 }' | xargs)
disk_available=$(df -BM | egrep '/dev/sda2' | awk '{ print $4 }' | sed 's/[^0-9]*//g' | xargs)


insert_stmt="INSERT INTO host_usage (timestamp, host_id, memory_free, cpu_idle, cpu_kernel, disk_io, disk_available)
SELECT '$timestamp', host_info.id, '$memory_free', '$cpu_idle', '$cpu_kernel', '$disk_io', '$disk_available'
FROM host_info
WHERE host_info.hostname='$psql_host';"

psql -h "$psql_host" -p "$psql_port" -d "$db_name" -U "$psql_user" -c "$insert_stmt"
















