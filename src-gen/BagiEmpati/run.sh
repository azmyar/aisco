#!/bin/bash
source ~/.zshrc  

cleanup() {
    pkill -P $$
    exit 1
}

trap cleanup SIGINT

java -cp aisco.product.bagiempati --module-path aisco.product.bagiempati -m aisco.product.bagiempati &

wait