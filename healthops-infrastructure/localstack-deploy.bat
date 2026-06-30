@echo off
setlocal

echo ============================================
echo Deploying HealthOps to LocalStack...
echo ============================================
echo.

REM Deploy CloudFormation stack
aws --endpoint-url=http://localhost:4566 cloudformation deploy ^
    --stack-name healthops ^
    --template-file ".\cdk.out\localstack.template.json"

IF %ERRORLEVEL% NEQ 0 (
    echo.
    echo ============================================
    echo CloudFormation deployment FAILED!
    echo ============================================
    echo.
    echo Fetching CloudFormation stack events...
    echo.

    aws --endpoint-url=http://localhost:4566 cloudformation describe-stack-events ^
        --stack-name healthops ^
        --output table

    echo.
    pause
    exit /b %ERRORLEVEL%
)

echo.
echo ============================================
echo Deployment Successful!
echo ============================================
echo.

echo Fetching Load Balancer DNS...
echo.

aws --endpoint-url=http://localhost:4566 elbv2 describe-load-balancers ^
    --query "LoadBalancers[0].DNSName" ^
    --output text

IF %ERRORLEVEL% NEQ 0 (
    echo.
    echo Failed to retrieve Load Balancer DNS.
    pause
    exit /b %ERRORLEVEL%
)

echo.
echo ============================================
echo Deployment completed successfully.
echo ============================================

pause
endlocal