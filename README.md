## 介绍

此工程为后端Dev用来练手的一个demo项目，用来帮助后端开发快速熟悉后端的技术栈和开发模式。你需要熟悉此代码库的架构，参照已有代码完成readme里的Story。

Note：请不要直接在master提交代码。完成story后提交代码至以你名字命名的分支，并@TL review。

## 待完成Story

- 请参考OTR开发手册

## Commit 的格式声明
格式：[YourName] #cardNumber commitType: commitMessage.

样例：[zhanpeng] #OTR-12088 feat: add order insurances at create order event.

commitType 可选值：chore/docs/feat/fix/refactor/style/test



## Builder的快捷生成教程

### 简介

我们通过builder来构造测试数据，它是为了避免大家在写测试时重复的构造数据，并且突出要测试的重点。

builder的生成分两种，一种为entityBuilder，它会生成自带persist到数据库的方法，另一种为普通的builder，只会build出一个对象出来。

### 如何生成

1. 请从trello上下载Backend的IDEA配置并将其导入至IDEA内。（不包含快捷键等内容，可放心食用）https://trello.com/c/DpR3nCUV/8-intellij-idea-%E9%85%8D%E7%BD%AE%E6%8E%A8%E8%8D%90

#### EntityBuilder的构造方法

1. 选中要创建的文件夹下，control + alt + N，然后输入entityBuilder，回车。

2. 将光标移至生成的文件内，command + N，然后输入delegate，回车。

3. 选中对象，回车。

4. 选中所有的set方法，回车。

5. 使用批量编辑快捷键修改set方法为如下格式（快捷键：control + command + G）

   ```
   public UserBuilder withActive(boolean isActive) {
       user.setActive(isActive);
       return this;
   }
   ```

6. 添加withDefault 的默认值。

#### Builder的构造方法

1. 选中要创建的文件夹下，control + option + N，然后输入entityBuilder，回车。
2. 添加withDefault 的默认值。

## Token解析

有关解析请求中的token信息，并转换为JwtUser的方式以及测试如何构造token数据，请参考UserController下的/api/user/jwtuser api，以及对应的测试写法。