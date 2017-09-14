variable "key_name" {}

provider "aws" {
  region = "ap-northeast-1"
}

data "aws_ami" "amazon_linux" {
  most_recent = true
  owners = ["amazon"]

  filter {
    name   = "architecture"
    values = ["x86_64"]
  }

  filter {
    name   = "root-device-type"
    values = ["ebs"]
  }

  filter {
    name   = "name"
    values = ["amzn-ami-hvm-*"]
  }

  filter {
    name   = "virtualization-type"
    values = ["hvm"]
  }

  filter {
    name   = "block-device-mapping.volume-type"
    values = ["gp2"]
  }
}

resource "aws_instance" "jenkins" {
  ami           = "${data.aws_ami.amazon_linux.id}"
  instance_type = "t2.micro"
  key_name      = "${var.key_name}"
  user_data = <<EOF
IyEvYmluL2Jhc2gKCndnZXQgLU8gL2V0Yy95dW0ucmVwb3MuZC9qZW5raW5zLnJl
cG8gaHR0cDovL3BrZy5qZW5raW5zLWNpLm9yZy9yZWRoYXQvamVua2lucy5yZXBv
CnJwbSAtLWltcG9ydCBodHRwOi8vcGtnLmplbmtpbnMtY2kub3JnL3JlZGhhdC9q
ZW5raW5zLWNpLm9yZy5rZXkKCnl1bSBpbnN0YWxsIC15IGdpdCBqZW5raW5zIGph
dmEtMS44LjAtb3BlbmpkawphbHRlcm5hdGl2ZXMgLS1zZXQgamF2YSAvdXNyL2xp
Yi9qdm0vanJlLTEuOC4wLW9wZW5qZGsueDg2XzY0L2Jpbi9qYXZhCgpjaGtjb25m
aWcgamVua2lucyBvbgovZXRjL2luaXQuZC9qZW5raW5zIHN0YXJ0CgpleGl0IDA=
EOF
}
