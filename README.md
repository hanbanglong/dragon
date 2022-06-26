# 工程简介



# 用于测试一些springboot自带的功能，以及其对外提供的一些扩展

# 一、添加web请求时间类型转换器（基于各种格式的String类型时间戳转换为Date类型）
## 1.基于@RequestBody的反序列化格式
这里需要注意，当创建了MappingJackson2HttpMessageConverter对象的时候，有可能不会生效，大概率的原因是由于HttpMessageConverter中已经存在系统定义的配置，所以将其移除后，使用自定义的转换方式进行处理即可生效
## 2.基于非@RequestBody的类型转换
则需要借助于ConverterFactory进行注册生效
# 二、基于spi实现对外提供service接口
 此方式，可以让客户按照自己的意愿去实现定义的方法，自己程序中可以直接通过其类型添加list去循环实现客户的代码逻辑

