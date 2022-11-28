# Redis

## Redis 介绍

- 关系型数据库:将数据存储表的行和列中，类似表格，例如 mysql，oracle，sqlServler
- 非关系型数据库：将数据大块组合在一起，可以像文档，键值对，常见的如：Redis，MongoDb

Redis 是用 C 语言开发的一个开源的高性能键值对（key-value）数据库

## 数据类型

redis 存储的是：key,value 格式的数据，其中 key 都是字符串，value 有 5 种不同的数据结构

1. 字符串类型 String
2. 哈希类型 hash:map 格式
3. 列表类型 list：linkedlist 格式，支持重复元素
4. 集合类型 set 不允许重复元素
5. 有序集合类型 sortedset 不允许重复元素，且元素有顺序

### 字符串(String)

```JAVA
set key value //存储  重复会覆盖旧值

get key //获取

del key //删除  返回值 是被删除 key 的数量

mset key1 value2 key2 value2  //批量设置

mget key1 key2 //批量获取

```

### Hash(哈希)

Redis hash 是一个 String 类型的 field (字段) 和 value（值）得映射表，hash 特别适合用于存储对象，Redis 中每个 hash 可以存储 40 多亿键值对

```java

//**************************//

hset key field value   //存储 一次只能操作一对key value

hset myhash name "jessy"

hset myhash age 25


//********************//

hget key field //获取指定的field对应的值

hget myhash name

//***************** //

hgetall key //获取所有的field和value

hgetall myhash

hdel key field   //删除 只能删除一个filed-value

//***************************//

hmset key field1 value1 filed2

hmset myhash name "lucy" age 26 sex female

```

### 列表类型 list

可以添加一个元素到列表的头部（左边）或者尾部（右边）支持重复元素

```JAVA

lpush key value //将元素加入列表左边

rpush key value //将元素加入列表右边

lrange key start end //范围获取

lrange list 0 4


llen list//获取长度

lpop key //删除列表最左边的元素，并将元素返回

lpop list

rpop key //删除列表最右边元素，并将元素返回

rpop key

lindex key value //查询下标对应的value

lindex list 3


```

### Set 集合类型 不允许重复元素

```JAVA

sadd key value //存储

smembers key //获得set集合中的所有元素

screm key value //删除set集合中的某个元素

sismember key value  //查询value是否存在

scard key //获取长度

```

### 有序集合类型 Zset

不允许重复元素，且元素有顺序。每个元素都会关联一个 double 类型分数。redis 正是通过分数来为集合中的成员进行从小到大的排序

```JAVA

zadd key score value //存储

 zadd mysort 60 zhangsan

 zadd mysort 50 lisi

 zadd mysort 80 wangwu

zrange key start end [withscores]  //获取

zrange mysort 0 3 withscores

zrem key value  //删除

zrem mysort lisi


```

### redis 其它常用命令

1. 查询所有的键

> keys pattern
> eg: key \*
>
> - \* 代表匹配任意字符
> - ? 代表匹配一个字符
> - [] 代表匹配部分字符，例如[1,3]代表匹配 1 和 3，而[1-10]代表匹配 1 到 10 的任意数字。
> - x 转移字符，例如要匹配星号，问号需要转义的字符

2. 查询键对应的 value 类型

> type key

3. 删除指定的 key value

> del key

4. 设置过期时间

> expire key time (time 以秒为单位)

5. 查看过期时间

> ttl key (单位为秒，-1 表示永久)

### 分布式锁

### redis 事务
