#!/bin/bash
source ~/.zshrc  

cleanup() {
    pkill -P $$
    exit 1
}

trap cleanup SIGINT

java -cp aisco.product.mampubantu --module-path aisco.product.mampubantu -m aisco.product.mampubantu &

wait