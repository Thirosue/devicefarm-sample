# provisioning jenkins + devicefarm plugin on Amazon Linux EC2

## required

+ aws access key

```
$ cat ~/.aws/terraform.tfvars
access_key="[IAM Access Key]"
secret_key="[IAM Secret Key]"
```

+ aws key pair

## provisioning

### plan

```
terraform plan -var-file=~/.aws/terraform.tfvars -var 'key_name=[keypair]'
```

### do

```
terraform plan -var-file=~/.aws/terraform.tfvars -var 'key_name=[keypair]'
```

### destroy :boom:

```
terraform plan -var-file=~/.aws/terraform.tfvars -var 'key_name=[keypair]'
```

## AWS IAM User

add aws iam user for devicefarmplugin

### program access

![IAMSetting1](https://raw.githubusercontent.com/Thirosue/devicefarm-sample/master/provisioning/img/iamsetting1.png "")

### DeviceFarmFullAccess

![IAMSetting2](https://raw.githubusercontent.com/Thirosue/devicefarm-sample/master/provisioning/img/iamsetting2.png "")

## jenkins 

### Home
http://[IPv4 Public IP]:8080

### Unlock Jenkins

![Unlock Jenkins](https://raw.githubusercontent.com/Thirosue/devicefarm-sample/master/provisioning/img/jenkins.png "")

+ input command output

```
$ ssh -i "[keypair]" ec2-user@ec2-xx-xxx-xx-xxx.ap-northeast-1.compute.amazonaws.com "sudo su -c 'cat /var/lib/jenkins/secrets/initialAdminPassword'"
rsynmqjmqqw4pyt8exn2pwfx2agpuzqw
```

### add DeviceFarm Plugin

![DeviceFarmPlugin](https://raw.githubusercontent.com/Thirosue/devicefarm-sample/master/provisioning/img/devicefarmplugin.png "")

### Access Key Setting

input DeviceFarm IAM user accesskey & secretkey

![DeviceFarmIAMSetting](https://raw.githubusercontent.com/Thirosue/devicefarm-sample/master/provisioning/img/devicefarmiamsetting.png "")


## Terraform

### user_data - (Optional) The user data to provide when launching the instance.

```
$ cat init/userdata.sh | openssl enc -e -base64
IyEvYmluL2Jhc2gKCndnZXQgLU8gL2V0Yy95dW0ucmVwb3MuZC9qZW5raW5zLnJl
cG8gaHR0cDovL3BrZy5qZW5raW5zLWNpLm9yZy9yZWRoYXQvamVua2lucy5yZXBv
CnJwbSAtLWltcG9ydCBodHRwOi8vcGtnLmplbmtpbnMtY2kub3JnL3JlZGhhdC9q
ZW5raW5zLWNpLm9yZy5rZXkKCnl1bSBpbnN0YWxsIC15IGdpdCBqZW5raW5zIGph
dmEtMS44LjAtb3BlbmpkawphbHRlcm5hdGl2ZXMgLS1zZXQgamF2YSAvdXNyL2xp
Yi9qdm0vanJlLTEuOC4wLW9wZW5qZGsueDg2XzY0L2Jpbi9qYXZhCgpjaGtjb25m
aWcgamVua2lucyBvbgovZXRjL2luaXQuZC9qZW5raW5zIHN0YXJ0CgpleGl0IDA=
```
