#!/bin/bash

OUTPUT_FILE="nginx_domains_locations.txt"
CONFIG_DIR="/etc/nginx/conf.d"

> "$OUTPUT_FILE"

awk '
/server_name/ {
  match($0, /server_name\s+([^;]+);/, m)
  split(m[1], arr, " ")
  domain=""
  for (i in arr) {
    gsub(/^www\./, "", arr[i])
    if (arr[i] != "") {
      domain = arr[i]
      break
    }
  }
}

/location[[:space:]]+\// {
  if (!domain) next
  match($0, /location[[:space:]]+([^[:space:]]+)/, m)
  loc = m[1]
  proxy = ""

  while (getline > 0 && $0 !~ /}/) {
    if (match($0, /proxy_pass[[:space:]]+([^;]+);/, p))
      proxy = p[1]
  }

  if (proxy != "")
    print domain, loc, "->", proxy
  else
    print domain, loc
}

END { print "" }
' "$CONFIG_DIR"/*.conf >> "$OUTPUT_FILE"

