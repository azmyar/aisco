#!/bin/bash
source ~/.zshrc  

cleanup() {
    pkill -P $$
    exit 1
}

trap cleanup SIGINT

java -cp aisco.product.hobiberbagi --module-path aisco.product.hobiberbagi -m aisco.product.hobiberbagi &

wait