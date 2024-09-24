# docker 部署
## 创建挂载目录

```shell
sudo mkdir -p /root/projects/mall
```

## 复制配置文件到 WSL
```shell
# 复制 nginx 配置文件
sudo cp /mnt/d/Programing\ Workspace/mall/doc/nginx/nginx.conf /root/projects/mall

# 复制 logstash 配置文件
sudo cp /mnt/d/Programing\ Workspace/mall/doc/elk/logstash.conf /root/projects/mall

# 复制 rabbitmq 插件
sudo cp /mnt/d/Programing\ Workspace/mall/doc/rabbitmq/rabbitmq_delayed_message_exchange-3.13.0.ez /root/projects/mall
```