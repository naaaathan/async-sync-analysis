aws --endpoint-url=http://localhost:4566 cloudformation create-stack --stack-name tcc-stack --template-body file://"$(pwd)"/cloudformation.yaml
