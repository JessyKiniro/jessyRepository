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

> SET key "abc"
>
> GET key

### Hash(哈希)
