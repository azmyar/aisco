#!/bin/bash
source ~/.zshrc  

cleanup() {
    pkill -P $$
    exit 1
}

trap cleanup SIGINT

java -cp aisco.product.relawandermawan --module-path aisco.product.relawandermawan -m aisco.product.relawandermawan &

wait