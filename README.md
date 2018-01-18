# 本文你将学到什么？
本文将以原理+实战的方式，首先对“微服务”相关的概念进行知识点扫盲，然后开始手把手教你搭建这一整套的微服务系统。

# 这套微服务框架能干啥？
这套系统搭建完之后，那可就厉害了： 

- 微服务架构<br>
你的整个应用程序将会被拆分成一个个功能独立的子系统，独立运行，系统与系统之间通过RPC接口通信。这样这些系统之间的耦合度大大降低，你的系统将非常容易扩展，团队协作效率提升了N个档次。这种架构通过眼下流行的SpringBoot和阿里巴巴吊炸天的Dubbo框架来实现。

- 容器化部署<br>
你的各个微服务将采用目前处于浪潮之巅的Docker来实现容器化部署，避免一切因环境引起的各种问题，让你们团队的全部精力集中在业务开发上。

- 自动化构建<br>
项目被微服务化后，各个服务之间的关系错中复杂，打包构建的工作量相当可怕。不过没关系，本文将借助Jenkins，帮助你一键自动化部署，从此你便告别了加班。

------

# 知识点扫盲篇
咳咳，敲黑板啦！笔记赶紧记起来，课后我要检查的！检查不合格的同学放学后留下来！

## 知识点1：微服务
微服务一次近几年相当火，成为程序猿饭前便后装逼热门词汇，你不对它有所了解如何在程序猿装逼圈子里混？下面我用最为通俗易懂的语言介绍它。

要讲清楚微服务，我先要从一个系统架构的演进过程讲起。

### 单机结构
我想大家最最最熟悉的就是单机结构，一个系统业务量很小的时候所有的代码都放在一个项目中就好了，然后这个项目部署在一台服务器上就好了。整个项目所有的服务都由这台服务器提供。这就是单机结构。
那么，单机结构有啥缺点呢？我想缺点是显而易见的，单机的处理能力毕竟是有限的，当你的业务增长到一定程度的时候，单机的硬件资源将无法满足你的业务需求。此时便出现了集群模式，往下接着看。

### 集群结构
集群模式在程序猿界由各种装逼解释，有的让你根本无法理解，其实就是一个很简单的玩意儿，且听我一一道来。

单机处理到达瓶颈的时候，你就把单机复制几份，这样就构成了一个“集群”。集群中每台服务器就叫做这个集群的一个“节点”，所有节点构成了一个集群。每个节点都提供相同的服务，那么这样系统的处理能力就相当于提升了好几倍（有几个节点就相当于提升了这么多倍）。

但问题是用户的请求究竟由哪个节点来处理呢？最好能够让此时此刻负载较小的节点来处理，这样使得每个节点的压力都比较平均。要实现这个功能，就需要在所有节点之前增加一个“调度者”的角色，用户的所有请求都先交给它，然后它根据当前所有节点的负载情况，决定将这个请求交给哪个节点处理。这个“调度者”有个牛逼了名字——负载均衡服务器。

集群结构的好处就是系统扩展非常容易。如果随着你们系统业务的发展，当前的系统又支撑不住了，那么给这个集群再增加节点就行了。但是，当你的业务发展到一定程度的时候，你会发现一个问题——无论怎么增加节点，貌似整个集群性能的提升效果并不明显了。这时候，你就需要使用微服务结构了。

### 微服务结构
先来对前面的知识点做个总结。
从单机结构到集群结构，你的代码基本无需要作任何修改，你要做的仅仅是多部署几台服务器，没太服务器上运行相同的代码就行了。但是，当你要从集群结构演进到微服务结构的时候，之前的那套代码就需要发生较大的改动了。所以对于新系统我们建议，系统设计之初就采用微服务架构，这样后期运维的成本更低。但如果一套老系统需要升级成微服务结构的话，那就得对代码大动干戈了。所以，对于老系统而言，究竟是继续保持集群模式，还是升级成微服务架构，这需要你们的架构师深思熟虑、权衡投入产出比。

OK，下面开始介绍所谓的微服务。
微服务就是将一个完整的系统，按照业务功能，拆分成一个个独立的子系统，在微服务结构中，每个子系统就被称为“服务”。这些子系统能够独立运行在web容器中，它们之间通过RPC方式通信。

举个例子，假设需要开发一个在线商城。按照微服务的思想，我们需要按照功能模块拆分成多个独立的服务，如：用户服务、产品服务、订单服务、后台管理服务、数据分析服务等等。这一个个服务都是一个个独立的项目，可以独立运行。如果服务之间有依赖关系，那么通过RPC方式调用。

这样的好处有很多：

1. 系统之间的耦合度大大降低，可以独立开发、独立部署、独立测试，系统与系统之间的边界非常明确，排错也变得相当容易，开发效率大大提升。
2. 系统之间的耦合度降低，从而系统更易于扩展。我们可以针对性地扩展某些服务。假设这个商城要搞一次大促，下单量可能会大大提升，因此我们可以针对性地提升订单系统、产品系统的节点数量，而对于后台管理系统、数据分析系统而言，节点数量维持原有水平即可。
3. 服务的复用性更高。比如，当我们将用户系统作为单独的服务后，该公司所有的产品都可以使用该系统作为用户系统，无需重复开发。

那么问题来了，当采用微服务结构后，一个完整的系统可能有很多独立的子系统组成，当业务量渐渐发展起来之后，而这些子系统之间的关系将错综复杂，而且为了能够针对性地增加某些服务的处理能力，某些服务的背后可能是一个集群模式，由多个节点构成，这无疑大大增加了运维的难度。微服务的想法好是好，但开发、运维的复杂度实在是太高。为了解决这些问题，阿里巴巴的Dubbo就横空出世了。

## 知识点2：Dubbo
Dubbo是一套微服务系统的协调者，在它这套体系中，一共有三种角色，分别是：服务提供者（下面简称提供者）、服务消费者（下面简称消费者）、注册中心。

你在使用的时候需要将Dubbo的jar包引入到你的项目中，也就是每个服务都要引入Dubbo的jar包。然后当这些服务初始化的时候，Dubbo就会将当前系统需要发布的服务、以及当前系统的IP和端口号发送给注册中心，注册中心便会将其记录下来。这就是服务发布的过程。与此同时，也是在系统初始化的时候，Dubbo还会扫描一下当前系统所需要引用的服务，然后向注册中心请求这些服务所在的IP和端口号。接下来系统就可以正常运行了。当系统A需要调用系统B的服务的时候，A就会与B建立起一条RPC信道，然后再调用B系统上相应的服务。

这，就是Dubbo的作用。

## 知识点3：容器化部署
当我们使用了微服务架构后，我们将一个原本完整的系统，按照业务逻辑拆分成一个个可独立运行的子系统。为了降低系统间的耦合度，我们希望这些子系统能够运行在独立的环境中，这些环境之间能够相互隔离。

在Docker出现之前，若使用虚拟机来实现运行环境的相互隔离的话成本较高，虚拟机会消耗较多的计算机硬件/软件资源。Docker不仅能够实现运行环境的隔离，而且能极大程度的节约计算机资源，它成为一种轻量级的“虚拟机”。

## 知识点4：自动化构建
当我们使用微服务架构后，随着业务的逐渐发展，系统之间的依赖关系会日益复杂，而且各个模块的构建顺序都有所讲究。对于一个小型系统来说，也许只有几个模块，那么你每次采用人肉构建的方式也许并不感觉麻烦。但随着系统业务的发展，你的系统之间的依赖关系日益复杂，子系统也逐渐增多，每次构建一下你都要非常小心谨慎，稍有不慎整个服务都无法正常启动。而且这些构建的工作很low，但却需要消耗大量的精力，这无疑降低了开发的效率。不过没关系，Jenkins就是来帮助你解决这个问题的。

我们只需在Jenkins中配置好代码仓库、各个模块的构建顺序和构建命令，在以后的构建中，只需要点击“立即构建”按钮，Jenkins就会自动到你的代码仓库中拉取最新的代码，然后根据你事先配置的构建命令进行构建，最后发布到指定的容器中运行。你也可以让Jenkins定时检查代码仓库版本的变化，一旦发现变动就自动地开始构建过程，并且让Jenkins在构建成功后给你发一封邮件。这样你连“立即构建”的按钮也不需要按，就能全自动地完成这一切构建过程。

------
# 实战动手篇
## 1. 学习目标
接下来我会带着大家，以一个在线商城为例，搭建一套能够自动化部署的微服务框架。这个框架能做如下几件事情：

1. 基于SpringBoot快速开发
我们将选择目前热度很高的SpringBoot，最大限度地降低配置复杂度，把大量的精力投入到我们的业务开发中来。
2. 基于Dubbo的微服务化
我们会使用阿里巴巴的开源框架Dubbo，将我们的系统拆分成多个独立的微服务，然后用Dubbo来管理所有服务的发布和引用。有了Dubbo之后，调用远程服务就像调用一个本地函数一样简单，Dubbo会帮我们完成远程调用背后所需要的一切。
3. 基于Docker的容器化部署
由于使用了微服务架构后，我们的系统将会由很多子系统构成。为了达到多个系统之间环境隔离的目的，我们可以将它们部署在多台服务器上，可这样的成本会比较高，而且每台服务器的性能可能都没有充分利用起来。所以我们很自然地想到了虚拟机，在同一台服务器上运行多个虚拟机，从而实现环境的隔离，每个虚拟机上运行独立的服务。然而虚拟机的隔离成本依旧很高，因为它需要占用服务器较多的硬件资源和软件资源。所以，在微服务结构下，要实现服务环境的隔离，Docker是最佳选择。它比虚拟机更加轻量级，占用资源较少，而且能够实现快速部署。
4. 基于Jenkins的自动化构建
当我们采用了微服务架构后，我们会发现这样一个问题。整个系统由许许多多的服务构成，这些服务都需要运行在单独的容器中，那么每次发布的复杂度将非常高。首先你要搞清楚这些服务之间的依赖关系、启动的先后顺序，然后再将多个子系统挨个编译、打包、发布。这些操作技术难度低，却又容易出错。那么有什么工具能够帮助我们解决这些问题呢？答案就是——Jenkins。
它是一款自动化构建的工具，简单的来说，就是我们只需要在它的界面上按一个按钮，就可以实现上述一系列复杂的过程。

## 2. 项目背景介绍
本文我以一个大家都非常熟悉的在线商城作为例子，一步步教大家如何搭建微服务框架，它有如下功能：

- 产品管理<br>
产品的增删改查。
- 订单管理<br>
订单的增删改查、购物车功能。
- 用户管理<br>
用户的登录、注册、权限管理、收货地址等等。
- 数据分析<br>
提供对本系统数据分析的功能。

> 注意：本文的IDE使用的是intelliJ IDEA，推荐大家也用这个，用了都说好，用了你就会爱上它。

## 3. 创建项目的组织结构
在动手之前，我先来说一说这一步的目标：

- 创建一个Maven Project，命名为“Gaoxi”<br>
这个Project由多个Module构成，每个Module对应着“微服务”的一个子系统，可独立运行，是一个独立的项目。
这也是目前主流的项目组织形式，即多模块项目。
- 在Gaoxi这个项目下创建各个子模块，每个自模块都是一个独立的SpringBoot项目：
    * Gaoxi-User
    用户服务
    * Gaoxi-Order
    订单服务
    * Gaoxi-Product
    产品服务
    * Gaoxi-Analysis
    数据分析服务
    * Gaoxi-Controller
    本系统的控制层，和以往三层结构中的Controller层的作用一样，都是用作请求调度，只不过在微服务架构中，我们将它抽象成一个单独的系统，可以独立运行。
    * Gaoxi-Common-Service-Facade
    它处于本系统的最底层，被所有模块依赖，一些公用的类库都放在这里。
    * Gaoxi-Redis
    我们将Redis封装成一个单独的服务，运行在独立的容器中，当哪一个模块需要使用Redis的时候，仅需要引入该服务即可，就免去了各种繁琐的、重复的配置。而这些配置均在Gaoxi-Redis系统中完成了。

![title](https://leanote.com/api/file/getImage?fileId=5a1cc0eaab64416ff3001095)

下面开始动手。

### 3.1 创建Project

- New一个Project<br>
![创建Project](https://leanote.com/api/file/getImage?fileId=5a1cbe67ab64416ff300102d)

- 选择Spring Initializr<br>
![title](https://leanote.com/api/file/getImage?fileId=5a1cbeb6ab64416ff300103e)

- 设置groupId、artifactId、version
```
<groupId>com.gaoxi</groupId>
<artifactId>gaoxi</artifactId>
<version>0.0.1-SNAPSHOT</version>
```

- Project创建完毕！接下来在Project下面创建Module

### 3.2 创建Module
- 在Project上New Module<br>
![title](https://leanote.com/api/file/getImage?fileId=5a1cbfa1ab64416ff3001061)

- 和刚才一样，选择Spring Initializr，设置groupId、artifactId、version
- 依次创建好所有的Module，如下图所示：
![title](https://leanote.com/api/file/getImage?fileId=5a1cc00eab64416ff3001079)


### 3.3 构建模块的依赖关系
目前为止，模块之间没有任何联系，下面我们要通过pom文件来指定它们之间的依赖关系，依赖关系如下图所示：
![title](https://leanote.com/api/file/getImage?fileId=5a1cc1f0ab64416ff30010d6)
Gaoxi-User、Gaoxi-Analysis、Gaoxi-Product、Gaoxi-Order这四个系统相当于以往三层结构的Service层，提供系统的业务逻辑，只不过在微服务结构中，Service层的各个模块都被抽象成一个个单独的子系统，它们提供RPC接口供上面的Gaoxi-Controller调用。它们之间的调用由Dubbo来完成，所以它们的pom文件中并不需要作任何配置。而这些模块和Gaoxi-Common-Service-Facade之间是本地调用，因此需要将Gaoxi-Common-Service-Facade打成jar包，并让这些模块依赖这个jar，因此就需要在所有模块的pom中配置和Gaoxi-Common-Service-Facade的依赖关系。

此外，为了简化各个模块的配置，我们将所有模块的通用依赖放在Project的pom文件中，然后让所有模块作为Project的子模块。这样子模块就可以从父模块中继承所有的依赖，而不需要自己再配置了。

下面开始动手：

- 首先将Common-Service-Facade的打包方式设成jar<br>
当打包这个模块的时候，Maven会将它打包成jar，并安装在本地仓库中。这样其他模块打包的时候就可以引用这个jar。
```
<groupId>com.gaoxi</groupId>
<artifactId>gaoxi-common-service-facade</artifactId>
<version>0.0.1</version>
<packaging>jar</packaging>
```

- 将其他模块的打包方式设为war<br>
除了Gaoxi-Common-Service-Facade外，其他模块都是一个个可独立运行的子系统，需要在web容器中运行，所以我们需要将这些模块的打包方式设成war
```
<groupId>com.gaoxi</groupId>
<artifactId>gaoxi-user</artifactId>
<version>0.0.1-SNAPSHOT</version>
<packaging>war</packaging>
```


- 在总pom中指定子模块<br>
modules标签指定了当前模块的子模块是谁，但是仅在父模块的pom文件中指定子模块还不够，还需要在子模块的pom文件中指定父模块是谁。
```
<modules>
	<module>Gaoxi-Analysis</module>
	<module>Gaoxi-Order</module>
	<module>Gaoxi-Product</module>
	<module>Gaoxi-User</module>
	<module>Gaoxi-Redis</module>
	<module>Gaoxi-Controller</module>
	<module>Gaoxi-Common-Service-Facade</module>
</modules>
```

- 在子模块中指定父模块
```
<parent>
	<groupId>com.gaoxi</groupId>
	<artifactId>gaoxi</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<relativePath>../pom.xml</relativePath>
</parent>
```

> 到此为止，模块的依赖关系配置完毕！但要注意模块打包的顺序。由于所有模块都依赖于Gaoxi-Common-Servie-Facade模块，因此在构建模块时，首先需要编译、打包、安装Gaoxi-Common-Servie-Facade，将它打包进本地仓库中，这样上层模块才能引用到。当该模块安装完毕后，再构建上层模块。否则在构建上层模块的时候会出现找不到Gaoxi-Common-Servie-Facade中类库的问题。

### 3.4 在父模块的pom中添加所有子模块公用的依赖
```
<dependencies>
    <!-- Spring Boot -->
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter</artifactId>
	</dependency>

    <!-- Spring MVC -->
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-web</artifactId>
	</dependency>

    <!-- Spring Boot Test -->
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-test</artifactId>
		<scope>test</scope>
	</dependency>

	<!-- MyBatis -->
	<dependency>
		<groupId>org.mybatis.spring.boot</groupId>
		<artifactId>mybatis-spring-boot-starter</artifactId>
		<version>1.3.1</version>
	</dependency>

	<!-- Mysql -->
	<dependency>
		<groupId>mysql</groupId>
		<artifactId>mysql-connector-java</artifactId>
		<scope>runtime</scope>
	</dependency>

	<!-- Dubbo -->
	<dependency>
		<groupId>io.dubbo.springboot</groupId>
		<artifactId>spring-boot-starter-dubbo</artifactId>
		<version>1.0.0</version>
	</dependency>

	<!-- gaoxi-common-service-facade -->
	<dependency>
		<groupId>com.gaoxi</groupId>
		<artifactId>gaoxi-common-service-facade</artifactId>
		<version>0.0.1</version>
	</dependency>

	<!-- AOP -->
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-aop</artifactId>
	</dependency>

	<!-- guava -->
	<dependency>
		<groupId>com.google.guava</groupId>
		<artifactId>guava</artifactId>
		<version>23.3-jre</version>
	</dependency>
</dependencies>
```

当父模块的pom中配置了公用依赖后，子模块的pom文件将非常简洁，如下所示：
```
<groupId>com.gaoxi</groupId>
<artifactId>gaoxi-user</artifactId>
<version>0.0.1-SNAPSHOT</version>
<packaging>war</packaging>

<name>gaoxi-user</name>

<parent>
	<groupId>com.gaoxi</groupId>
	<artifactId>gaoxi</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<relativePath>../pom.xml</relativePath>
</parent>
```

当项目的结构搭建完成之后，接下来你需要配置Docker环境，并将这些项目打包进容器中，验证下是否能正常启动。

## 4. 创建Docker容器
### 4.1 安装Docker
在使用Docker之前，你当然先要安装Docker，安装过程较为简单，基本上就是傻瓜式操作，这里就不作过多介绍了，你可以在Docker的官网下载相应系统的安装包。
https://www.docker.com/

### 4.2 获取Tomcat镜像
在微服务架构中，一个完整的系统被拆分成了多个被称为“微服务”的子系统，这些子系统可以独立运行在Web容器中。所以我们需要为这些系统提供运行的Web容器，这里我们选择大家较为熟悉的Tomcat。

我们知道，Tomcat依赖于Java环境，安装Tomcat之前要进行一系列环境的配置：安装Java、配置环境变量、安装Tomcat等等。这些操作还是有些繁琐的。不过没关系，当使用了Docker之后，这些过程都可以轻而易举地完成。

我们只需从Docker Hub上找到Tomcat的镜像资源，然后从上面拉取下来就可以使用。你可以使用Tomcat官方的镜像，也可以使用我发布在Docker Hub上的Tomcat镜像。

> 注意点：推荐使用我的Tomcat镜像资源chaimm/tomcat，因为这个镜像中除了配置Tomcat的安装环境以外，还有一些本项目中要用到的Jenkins相关的配置。

采用如下命令从Docker Hub上拉取镜像：
```
docker pull chaimm/tomcat:1.1
```
简单解释下，docker pull是从从Docker Hub上拉取镜像的命令，后面的chaimm/tomcat是镜像的名称，:1.1是镜像的版本号。目前这个镜像的最新版本号是1.1，推荐大家拉取这个。

### 4.3 创建Tomcat容器
这里再简单介绍下“镜像”和“容器”的关系。
“镜像”就好比是面向对象中的“类”，“容器”就好比“类”创建的“对象”。在面向对象中，“类”定义了各种属性，“类”可以实例化出多个“对象”；而在Docker中，“镜像”定义了各种配置信息，它可以实例化出多个“容器”。“容器”就是一台可以运行的“虚拟机”。

接下来我们需要为所有的微服务创建各自的容器：

- gaoxi-user
- gaoxi-product
- gaoxi-order
- gaoxi-analysis
- gaoxi-controller
- gaoxi-redis

以创建gaoxi-user容器为例，采用如下命令创建容器：
```
docker run --name gaoxi-user-1 -p 8082:8080 -v /usr/web/gaoxi-log:/opt/tomcat/gaoxi-log chaimm/tomcat:1.1
```
- --name：指定容器的名字
- -p：指定容器的端口映射
-p 8082:8080 表示将容器的8080端口映射到宿主机的8082端口上
- -v：指定容器数据卷的映射
xxx:yyy 表示将容器yyy目录映射到宿主机的xxx目录上，从而访问宿主机的xxx目录就相当于访问容器的yyy目录。
- chaimm/tomcat:1.1：表示容器所对应的镜像。

这条命令执行成功后，你就可以通过```你的IP:8082``` 访问到gaoxi-user-1容器的tomcat了。如果你看到了那只眼熟了猫，那就说明容器启动成功了！
![title](https://leanote.com/api/file/getImage?fileId=5a1cebe9ab64416dcc001c56)

接下来，你需要按照上面的方法，给剩下几个系统创建好Tomcat容器。

> 注意点：这里要注意的是，你需要给这些Tomcat容器指定不同的端口号，防止端口号冲突。当然，在实际开发中，你并不需要将容器的8080端口映射到宿主机上，这里仅仅是为了验证容器是否启动成功才这么做的。

## 5. 整合Dubbo
### 5.1 创建zookeeper容器
Dubbo一共定义了三种角色，分别是：服务提供者、服务消费者、注册中心。注册中心是服务提供者和服务消费者的桥梁，服务消费者会在初始化的时候将自己的IP和端口号发送给注册中心，而服务消费者通过注册中心知道服务提供者的IP和端口号。

在Dubbo中，注册中心有多种选择，Dubbo最为推荐的即为ZooKeeper，本文采用ZooKeepeer作为Dubbo的注册中心。

创建ZooKeeper容器也较为简单，大家可以直接使用我创建的ZooKeeper镜像，通过如下命令即可下载镜像：
```
docker pull chaimm/zookeeper-dubbo:1.0
```
该镜像中不仅运行了一个zookeeper，还运行了一个拥有dubbo-admin项目的tomcat。dubbo-admin是Dubbo的一个可视化管理工具，可以查看服务的发布和引用的情况。

使用如下命令启动容器：
```
docker run --name zookeeper-debug -p 2182:2181 -p 10000:8080 chaimm/zookeeper-dubbo:1.0
```
- -p 2182:2181：将容器的2181端口映射到宿主机的2182端口上，该端口是ZooKeeper的端口号。
- -p 10000:8080：将容器的8080端口映射到宿主机的10000端口上，该端口是Dubbo-Admin所在Tomcat的端口号。

启动成功后，你就可以通过```你的IP:10000/dubbo-admin-2.8.4/```访问到Dubbo-Admin，如下图所示：
![title](https://leanote.com/api/file/getImage?fileId=5a1cff07ab64416ff3002053)

### 5.2 父pom文件中引入dubbo依赖
```
<!-- Spring Boot Dubbo 依赖 -->
<dependency>
	<groupId>io.dubbo.springboot</groupId>
	<artifactId>spring-boot-starter-dubbo</artifactId>
	<version>1.0.0</version>
</dependency>
```

### 5.3 发布服务
> 假设，我们需要将Gaoxi-User项目中的UserService发布成一项RPC服务，供其他系统远程调用，那么我们究竟该如何借助Dubbo来实现这一功能呢？

- 在Gaoxi-Common-Service-Facade中定义UserService的接口<br>
由于服务的发布和引用都依赖于接口，但服务的发布方和引用方在微服务架构中往往不在同一个系统中，所以需要将需要发布和引用的接口放在公共类库中，从而双方都能够引用。接口如下所示：
```
public interface UserService {

    public UserEntity login(LoginReq loginReq);
}
```

- 在Gaoxi-User中定义接口的实现<br>
在实现类上需要加上Dubbo的@Service注解，从而Dubbo会在项目启动的时候扫描到该注解，将它发布成一项RPC服务。
```
@Service(version = "1.0.0")
public class UserServiceImpl implements UserService {

    @Override
    public UserEntity login(LoginReq loginReq) {
        // 具体的实现代码
    }
}
```

- 在Gaoxi-User的application.properties中配置服务提供者的信息
```
spring.dubbo.application.name=user-provider # 本服务的名称
spring.dubbo.registry.address=zookeeper://IP:2182 # ZooKeeper所在服务器的IP和端口号
spring.dubbo.protocol.name=dubbo # RPC通信所采用的协议
spring.dubbo.protocol.port=20883 # 本服务对外暴露的端口号
spring.dubbo.scan=com.gaoxi.user.service # 服务实现类所在的路径
```

> 按照上面配置完成后，当Gaoxi-User系统初始化的时候，就会扫描spring.dubbo.scan所指定的路径下的@Service注解，该注解标识了需要发布成RPC服务的类。Dubbo会将这些类的接口信息+本服务器的IP+spring.dubbo.protocol.port所指定的端口号发送给Zookeeper，Zookeeper会将这些信息存储起来。
这就是服务发布的过程，下面来看如何引用一项RPC服务。

### 5.4 引用服务
> 假设，Gaoxi-Controller需要调用Gaoxi-User 提供的登录功能，此时它就需要引用UserService这项远程服务。下面来介绍服务引用的方法。

- 声明需要引用的服务<br>
引用服务非常简单，你只需要在引用的类中声明一项服务，然后用@Reference标识，如下所示：
```
@RestController
public class UserControllerImpl implements UserController {

    @Reference(version = "1.0.0")
    private UserService userService;
    
    @Override
    public Result login(LoginReq loginReq, HttpServletResponse httpRsp) {

        // 登录鉴权
        UserEntity userEntity = userService.login(loginReq);
    }
}
```

- 在Gaoxi-Controller的application.properties中配置服务消费者的信息
```
spring.dubbo.application.name=controller-consumer # 本服务的名称
spring.dubbo.registry.address=zookeeper://IP:2182 # zookeeper所在服务器的IP和端口号
spring.dubbo.scan=com.gaoxi # 引用服务的路径
```

> 上述操作完成后，当Gaoxi-Controller初始化的时候，Dubbo就会扫描spring.dubbo.scan所指定的路径，并找到所有被@Reference修饰的成员变量；然后向Zookeeper请求该服务所在的IP和端口号。当调用userService.login()的时候，Dubbo就会向Gaoxi-User发起请求，完成调用的过程。这个调用过程是一次RPC调用，但作为程序猿来说，这和调用一个本地函数没有任何区别，远程调用的一切都由Dubbo来帮你完成。这就是Dubbo的作用。

## 6. 自动化构建
> Jenkins是一个自动化构建工具，它可以帮助我们摆脱繁琐的部署过程，我们只需要在一开始配置好构建策略，以后部署只需要一键完成。

### 6.1 创建Jenkins容器
Jenkins采用Java开发，也需要Java环境，但我们使用Docker后，一切都采用容器化部署，Jenkins也不例外。

- 拉取镜像<br>
这里我们使用Jenkins官方提供的镜像，大家只需执行如下命令拉取即可：
```
docker pull docker.io/jenkins/jenkins
```

- 启动容器<br>
由于Jenkins运行在Tomcat容器中，因此我们将容器的8080端口映射到宿主机的10080端口上：
```
docker run --name jenkins -p 10080:8080 docker.io/jenkins/jenkins
```

- 初始化Jenkins<br>
然后你需要访问```IP:10080```，Jenkins会带着你进行一系列的初始化设置，你只要跟着它一步步走就行了，比较傻瓜式。

### 6.2 在Jenkins中创建项目
> 接下来我们要做的是，在Jenkins中为每一个服务创建一个项目，每个项目中定义了构建的具体流程。由于我们将整个项目分成了6个微服务，所以我们需要在Jenkins中分别为这6个服务创建项目。那句开始吧～

- 点击页面左侧的“新建”按钮：<br>
![title](https://leanote.com/api/file/getImage?fileId=5a1d09d6ab64416dcc00231b)

- 输入项目名称gaoxi-user，选择“构建一个Maven项目”，然后点击“OK”：<br>
![title](https://leanote.com/api/file/getImage?fileId=5a1d0a39ab64416dcc00233f)

- 配置Git仓库<br>
选择Git，然后输入本项目Git仓库的URL，并在Credentials中输入Git的用户名和密码，如下图所示：
![title](https://leanote.com/api/file/getImage?fileId=5a1d0ae0ab64416ff3002323)

- 构建触发器<br>
选择第一项，如下图所示：
![title](https://leanote.com/api/file/getImage?fileId=5a1d0b3aab64416dcc00238d)

- Pre Step<br>
Pre Step会在正式构建前执行，由于所有项目都依赖于Gaoxi-Common-Service—Facade，因此在项目构建前，需要将它安装到本地仓库，然后才能被当前项目正确依赖。
因此，在Pre Step中填写如下信息：
![title](https://leanote.com/api/file/getImage?fileId=5a1d0b76ab64416ff300234a)

- Build<br>
然后就是正式构建的过程，填写如下信息即可：
![title](https://leanote.com/api/file/getImage?fileId=5a1d0bdfab64416ff300235b)

> OK，Gaoxi-User的构建过程就配置完成了。当我们点击“立即构建”按钮时，Jenkins首先会从我们指定的Git仓库中拉取代码，然后执行Pre Step中的Maven命令，将Gaoxi-Common-Serivce-Facade打包安装到本地仓库。然后执行Build过程，将Gaoxi-User进行编译打包。
但此时Gaoxi-User仍然只是一个本地war包，并没有部署到Tomcat容器中，而我们采用了容器化部署后，Jenkins服务和Gaoxi-User服务并不在同一个Docker容器中，那么究竟该如何才能将Jenkins本地编译好的war包发送到Gaoxi-User容器中呢？这就需要使用Jenkins的一个插件——Deploy Plugin。

### 6.3 远程部署
- 下载插件<br>
首先你需要下载Deploy Plugin，下载地址如下：
https://wiki.jenkins.io/display/JENKINS/Deploy+Plugin

- 安装插件<br>
在系统管理–>插件管理–>高级上传deploy.hpi进行安装。

- 在父项目的pom文件中增加远程部署插件：<br>
```
<plugin>
	<groupId>org.codehaus.cargo</groupId>
	<artifactId>cargo-maven2-plugin</artifactId>
	<version>1.6.5</version>
	<configuration>
		<container>
			<!-- 指明使用的tomcat服务器版本 -->
			<containerId>tomcat8x</containerId>
			<type>remote</type>
		</container>
		<configuration>
			<type>runtime</type>
			<cargo.remote.username>Tomcat的用户名</cargo.remote.username>
			<cargo.remote.password>Tomcat的密码</cargo.remote.password>
		</configuration>
	</configuration>
	<executions>
		<execution>
			<phase>deploy</phase>
			<goals>
				<goal>redeploy</goal>
			</goals>
		</execution>
	</executions>
</plugin>
```

- 为Tomcat设置用户名和密码<br>
修改gaoxi-user容器中tomcat的tomcat-users.xml文件，增加tomcat的manager用户
![title](https://leanote.com/api/file/getImage?fileId=5a1d0ea8ab64416ff300242c)

> 注意：如果你使用了chaimm/tomcat镜像，那么其中Tomcat配置都已经完成，默认用户名：admin、默认密码：jishimen2019。强烈建议修改用户名和密码。


- 修改Jenkins中gaoxi-user的配置<br>
在“构建后操作”中增加如下配置：
![title](https://leanote.com/api/file/getImage?fileId=5a1d0fb5ab64416ff3002481)
    - WAR/EAR files：表示你需要发布的war包
    - Containers：配置目标Tomcat的用户名和密码
    
## 7. Maven的profile功能
> 在实际开发中，我们的系统往往有多套环境构成，如：开发环境、测试环境、预发环境、生产环境。而不同环境的配置各不相同。如果我们只有一套配置，那么当系统从一个环境迁移到另一个环境的时候，就需要通过修改代码来更换配置，这样无疑增加了工作的复杂度，而且易于出错。但好在Maven提供了profile功能，能帮助我们解决这一个问题。

- 父项目的pom中添加profile元素<br>
首先，我们需要在总pom的中添加多套环境的信息，如下所示：
```
<profiles>
	<profile>
		<id>dev</id>
		<properties>
			<profileActive>dev</profileActive>
		</properties>
		<activation>
			<activeByDefault>true</activeByDefault>
		</activation>
	</profile>
	<profile>
		<id>test</id>
		<properties>
			<profileActive>test</profileActive>
		</properties>
	</profile>
	<profile>
		<id>prod</id>
		<properties>
			<profileActive>prod</profileActive>
		</properties>
	</profile>
</profiles>
```

- 父项目的pom中添加resource元素<br>
resource标识了不同环境下需要打包哪些配置文件。
```
<resources>
	<resource>
	    <!-- 标识配置文件所在的目录 -->
		<directory>src/main/resources</directory>
		<filtering>true</filtering>
		<!-- 构建时将这些配置文件全都排除掉 -->
		<excludes>
			<exclude>application.properties</exclude>
			<exclude>application-dev.properties</exclude>
			<exclude>application-test.properties</exclude>
			<exclude>application-prod.properties</exclude>
		</excludes>
	</resource>
	<resource>
		<directory>src/main/resources</directory>
		<filtering>true</filtering>
		<!-- 标识构建时所需要的配置文件 -->
		<includes>
			<include>application.properties</include>
			<!-- ${profileActive}这个值会在maven构建时传入 -->
			<include>application-${profileActive}.properties</include>
		</includes>
	</resource>
</resources>
```

- 父项目的pom中添加插件maven-resources-plugin<br>
该插件用来在Maven构建时参数替换
```
<plugin>
	<artifactId>maven-resources-plugin</artifactId>
	<version>3.0.2</version>
	<configuration>
		<delimiters>
			<delimiter>@</delimiter>
		</delimiters>
		<useDefaultDelimiters>false</useDefaultDelimiters>
	</configuration>
</plugin>
```

- 在子项目中创建配置<br>
分别为dev环境、test环境、prod环境创建三套配置，application.proerpties中存放公用的配置。
![title](https://leanote.com/api/file/getImage?fileId=5a1d14ddab64416dcc00261e)

- 在application.properties中添加spring.profiles.active=@profileActive@
```
spring.profiles.active=@profileActive@
```

- 修改Jenkins的配置<br>
在所有Jenkins中所有Maven命令的末尾添加``` -P test```，在打包的时候-P后面的参数将会作为@profileActive@的值传入系统中，从而根据该值打包相应的application-{profileActive}.properties文件。

## 8. 开发流程
> 到此为止，所有准备工作都已经完成，接下来就可以进入代码开发阶段。下面我以一个例子，带着大家感受下有了这套微服务框架后，我们的开发流程究竟有了哪些改变？下面以开发一个用户登录功能为例，介绍下使用本框架之后开发的流程。

### 8.1 开发目标
- 在Gaoxi-User系统中实现登录的业务逻辑，并发布成RPC服务
- 在Gaoxi-Controller中远程调用登录服务，并向前端提供登录的REST接口

![title](https://leanote.com/api/file/getImage?fileId=5a1d1d0eab64416dcc002884)

### 8.2 开发登录服务
首先需要在Gaoxi-Common-Service-Facade中创建UserService接口，并在其中声明登录的抽象函数。

```
public interface UserService {

    public UserEntity login(LoginReq loginReq);
}
```

> PS：为什么要将UserService放在Gaoxi-Common-Service-Facade中？
在这个项目中，Gaoxi-User是UserService服务的提供方，Gaoxi-Controller是UserService服务的引用方。由于二者并不在同一个系统中，所以必须要借助于Dubbo来实现远程方法调用。而Dubbo发布服务和引用服务的时候，都是根据服务的接口标识服务的，即服务引用方和发布方都需要使用服务的接口，因此需要将服务的接口放在所有项目共同依赖的基础模块——Gaoxi-Common-Service-Facade中。

然后在Gaoxi-User中开发UserService的实现——UserServiceImpl。
UserServiceImpl上必须要加上Dubbo的@Service注解，从而告诉Dubbo，在本项目初始化的时候需要将这个类发布成一项服务，供其他系统调用。
```
@Service(version = "1.0.0")
@org.springframework.stereotype.Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public UserEntity login(LoginReq loginReq) {

        // 校验参数
        checkParam(loginReq);

        // 创建用户查询请求
        UserQueryReq userQueryReq = buildUserQueryReq(loginReq);

        // 查询用户
        List<UserEntity> userEntityList = userDAO.findUsers(userQueryReq);

        // 查询失败
        if (CollectionUtils.isEmpty(userEntityList)) {
            throw new CommonBizException(ExpCodeEnum.LOGIN_FAIL);
        }

        // 查询成功
        return userEntityList.get(0);
    }
}
```

### 8.3 引用登录服务
当UserService开发完毕后，接下来Gaoxi-Controller需要引用该服务，并向前端提供一个登录的REST接口。
若要使用userService中的函数，仅需要在userService上添加@Reference注解，然后就像调用本地函数一样使用userService即可。Dubbo会帮你找到UserService服务所在的IP和端口号，并发送调用请求。但这一切对于程序猿来说是完全透明的。
```
@RestController
public class UserControllerImpl implements UserController {
    @Reference(version = "1.0.0")
    private UserService userService;
    
    @Override
    public Result login(LoginReq loginReq, HttpServletResponse httpRsp) {

        // 登录鉴权
        UserEntity userEntity = userService.login(loginReq);

        // 登录成功
        doLoginSuccess(userEntity, httpRsp);
        return Result.newSuccessResult();
    }
}
```

### 8.4 自动构建服务
上面的代码完成后，接下来你需要将代码提交至你的Git仓库。接下来就是自动化部署的过程了。

你需要进入Jenkins，由于刚才修改了Gaoxi-User和Gaoxi-Controller的代码，因此你需要分别构建这两个项目。
接下来Jenkins会自动从你的Git仓库中拉取最新的代码，然后依次执行Pre Step、Build、构建后操作的过程。由于我们在Pre Step中设置了编译Gaoxi-Common-Service-Facade，因此Jenkins首先会将其安装到本地仓库；然后再执行Build过程，构建Gaoxi-User，并将其打包成war包。最后将执行“构建后操作”，将war包发布到相应的tomcat容器中。
至此，整个发布流程完毕！

### 8.5 查看服务的状态
当Jenkins构建完成后，我们可以登录Dubbo-Admin查看服务发布和引用的状态。

当我们搜索UserService服务后，可以看到，该服务的提供者已经成功发布了服务：
![title](https://leanote.com/api/file/getImage?fileId=5a1d22e3ab64416ff3002a70)

点击“消费者”我们可以看到，该服务已经被controller-consumer成功订阅：
![title](https://leanote.com/api/file/getImage?fileId=5a1d2330ab64416ff3002a85)

## 9. 总结
总结一下，这套框架有如下优势：

1. 微服务架构<br>
我们借助于SpringBoot和Dubbo实现了微服务架构。微服务架构的理念就是将一个原本庞大、复杂的系统，按照业务功能拆分成一个个具有独立功能、可以独立运行的子系统，系统之间若有依赖，则通过RPC接口通信。从而最大限度地降低了系统之间的耦合度，从而更加易于扩展、更加易于维护。

2. 容器化部署<br>
我们借助于Docker实现了容器化部署。容器能够帮助我们屏蔽不同环境下的配置问题，使得我们只需要有一个Dockerfile文件，就可以处处运行。和虚拟机一样，Docker也拥有环境隔离的能力，但比虚拟机更加轻量级，由于每个容器仅仅是一条进程，因此它可以达到秒级的启动速度。

3. 自动化构建<br>
我们借助于Jenkins实现了所有项目的自动化构建与部署。我们只需要点击“立即构建”这个按钮，Jenkins就可以帮助我们梳理好错综复杂的项目依赖关系，准确无误地完成构建，并将war包发送到相应的web容器中。在启动的过程中，Dubbo会扫描当前项目所需要发布和引用的服务，将所需要发布的服务发布到ZooKeeper上，并向ZooKeeper订阅所需的服务。
有了Jenkins之后，这一切都是自动化完成。也许你并没有太强烈地感受到Jenkins所带来的便利。但是你想一想，对于一个具有错综复杂的依赖关系的微服务系统而言，如果每个服务的构建都需要你手动完成的话，你很快就会崩溃，你大把的时间将会投入在无聊但又容易出错的服务构建上。而Jenkins的出现能让这一切自动化完成。