package com.dwl.mq.config;


import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RabbitMQ 配置类
 * <p>
 * RabbitMQ Configuration
 * <p>
 * 定义交换机 队列 绑定关系和消息转换器
 * Defines exchanges, queues, bindings, and message converter
 * </p>
 * <p>
 * 三大通信场景:
 * Three communication scenarios:
 * <ul>
 *     <li>domain: Java 服务间领域事件通信 (Domain events between Java services)</li>
 *     <li>integration: Java 与 C/Python 跨语言集成通信 (Cross-language integration with C/Python)</li>
 *     <li>internal: Java 内部模块异步通信 (Async communication between internal modules)</li>
 * </ul>
 *
 * @Author Dwl
 * @Version 1.0
 * @Since 2026-09-01 02:18
 */
@Configuration
public class RabbitMqConfig {

    /**
     * 交换机名称
     * Exchange name
     */
    public static final String EXCHANGE = "automated.test.exchange";

    /**
     * 任务执行队列
     * Task execution queue
     */
    public static final String TASK_EXECUTE_QUEUE = "automated.test.task.execute";

    /**
     * 任务结果
     */
    public static final String TASK_RESULT_QUERY = "automated.test.task.execute.topic";

    /* ============================================================
     * 场景一: 领域事件通信 (Domain Events, Java <-> Java)
     * Scenario 1: domain event communication
     * ============================================================ */

    /**
     * 领域事件交换机(主题模式)
     * Domain event topic exchange
     */
    public static final String DOMAIN_EVENT_EXCHANGE = "atp.domain.event.exchange";

    /**
     * 领域事件队列
     * Domain event queue
     */
    public static final String DOMAIN_EVENT_QUEUE = "atp.domain.event.queue";

    /**
     * 领域事件路由键(通配, 匹配所有领域事件)
     * Domain event routing key (wildcard, matches all domain events)
     */
    public static final String DOMAIN_EVENT_ROUTING_KEY = "domain.event.#";

    // ============================================================
    // 场景二: 跨语言集成通信 (Integration, Java <-> C/Python)
    // Scenario 2: cross-language integration communication
    // ============================================================

    /**
     * C 引擎通信交换机
     * C engine integration exchange
     */
    public static final String C_ENGINE_EXCHANGE = "atp.integration.c-engine.exchange";

    /**
     * C 引擎任务执行指令队列(Java 发送, C 消费)
     * C engine task execution command queue (sent by Java, consumed by C)
     */
    public static final String C_ENGINE_TASK_EXECUTE_QUEUE = "atp.integration.c-engine.task.execute";

    /**
     * C 引擎任务执行结果队列(C 发送, Java 消费)
     * C engine task execution result queue (sent by C, consumed by Java)
     */
    public static final String C_ENGINE_TASK_RESULT_QUEUE = "atp.integration.c-engine.task.result";

    /**
     * Python AI 通信交换机
     * Python AI integration exchange
     */
    public static final String PYTHON_AI_EXCHANGE = "atp.integration.python-ai.exchange";

    /**
     * Python AI 风险评估指令队列(Java 发送, Python 消费)
     * Python AI risk assessment command queue (sent by Java, consumed by Python)
     */
    public static final String PYTHON_AI_RISK_ASSESS_QUEUE = "atp.integration.python-ai.risk.assess";

    /**
     * Python AI 风险评估结果队列(Python 发送, Java 消费)
     * Python AI risk assessment result queue (sent by Python, consumed by Java)
     */
    public static final String PYTHON_AI_RISK_RESULT_QUEUE = "atp.integration.python-ai.risk.result";

    // ============================================================
    // 场景三: 内部模块异步通信 (Internal, Java <-> Java)
    // Scenario 3: internal module async communication
    // ============================================================

    /**
     * 内部事件队列
     * Internal event queue
     */
    public static final String INTERNAL_EVENT_QUEUE = "atp.internal.event.queue";

    /**
     * 内部事件路由键(通配, 匹配所有内部事件)
     * Internal event routing key (wildcard, matches all internal events)
     */
    public static final String INTERNAL_EVENT_ROUTING_KEY = "internal.event.#";

    // ============================================================
    // 路由键常量(生产者与绑定共用)
    // Routing key constants (shared by producers and bindings)
    // ============================================================

    /**
     * C 引擎任务执行指令路由键
     * C engine task execute command routing key
     */
    public static final String C_ENGINE_COMMAND_TASK_EXECUTE_ROUTING_KEY = "c-engine.command.task-execute";

    /**
     * C 引擎任务执行结果路由键
     * C engine task execute result routing key
     */
    public static final String C_ENGINE_RESULT_TASK_EXECUTE_ROUTING_KEY = "c-engine.result.task-execute";

    /**
     * Python AI 风险评估指令路由键
     * Python AI risk assessment command routing key
     */
    public static final String PYTHON_AI_COMMAND_RISK_ASSESS_ROUTING_KEY = "python-ai.command.risk-assess";

    /**
     * Python AI 风险评估结果路由键
     * Python AI risk assessment result routing key
     */
    public static final String PYTHON_AI_RESULT_RISK_ASSESS_ROUTING_KEY = "python-ai.result.risk-assess";

    /**
     * 领域事件交换机(主题模式)
     * Domain event topic exchange
     *
     * @return 交换机实例 exchange instance
     */
    @Bean
    public TopicExchange domainEventExchange() {
        return new TopicExchange(DOMAIN_EVENT_EXCHANGE, true, false);
    }

    /**
     * C 引擎集成交换机(主题模式)
     * C engine integration topic exchange
     *
     * @return 交换机实例 exchange instance
     */
    @Bean
    public TopicExchange cEngineExchange() {
        return new TopicExchange(C_ENGINE_EXCHANGE, true, false);
    }

    /**
     * Python AI 集成交换机(主题模式)
     * Python AI integration topic exchange
     *
     * @return 交换机实例 exchange instance
     */
    @Bean
    public TopicExchange pythonAiExchange() {
        return new TopicExchange(PYTHON_AI_EXCHANGE, true, false);
    }

    /**
     * 领域事件队列(持久化)
     * Domain event queue (durable)
     *
     * @return 队列实例 queue instance
     */
    @Bean
    public Queue domainEventQueue() {
        return QueueBuilder.durable(DOMAIN_EVENT_QUEUE).build();
    }

    /**
     * C 引擎任务执行指令队列(持久化)
     * C engine task execute command queue (durable)
     *
     * @return 队列实例 queue instance
     */
    @Bean
    public Queue cEngineTaskExecuteQueue() {
        return QueueBuilder.durable(C_ENGINE_TASK_EXECUTE_QUEUE).build();
    }

    /**
     * C 引擎任务执行结果队列(持久化)
     * C engine task result queue (durable)
     *
     * @return 队列实例 queue instance
     */
    @Bean
    public Queue cEngineTaskResultQueue() {
        return QueueBuilder.durable(C_ENGINE_TASK_RESULT_QUEUE).build();
    }

    /**
     * Python AI 风险评估指令队列(持久化)
     * Python AI risk assessment command queue (durable)
     *
     * @return 队列实例 queue instance
     */
    @Bean
    public Queue pythonAiRiskAssessQueue() {
        return QueueBuilder.durable(PYTHON_AI_RISK_ASSESS_QUEUE).build();
    }

    /**
     * Python AI 风险评估结果队列(持久化)
     * Python AI risk result queue (durable)
     *
     * @return 队列实例 queue instance
     */
    @Bean
    public Queue pythonAiRiskResultQueue() {
        return QueueBuilder.durable(PYTHON_AI_RISK_RESULT_QUEUE).build();
    }

    /**
     * 内部事件队列(持久化)
     * Internal event queue (durable)
     *
     * @return 队列实例 queue instance
     */
    @Bean
    public Queue internalEventQueue() {
        return QueueBuilder.durable(INTERNAL_EVENT_QUEUE).build();
    }

    /**
     * 领域事件绑定: 队列绑定通配路由键, 消费全部领域事件
     * Domain event binding: bind queue with wildcard routing key to consume all domain events
     *
     * @return 绑定关系 binding
     */
    @Bean
    public Binding domainEventBinding() {
        return BindingBuilder.bind(domainEventQueue()).to(domainEventExchange()).with(DOMAIN_EVENT_ROUTING_KEY);
    }

    /**
     * C 引擎指令绑定
     * C engine command binding
     *
     * @return 绑定关系 binding
     */
    @Bean
    public Binding cEngineTaskExecuteBinding() {
        return BindingBuilder.bind(cEngineTaskExecuteQueue()).to(cEngineExchange()).with("c-engine.command.task-execute");
    }

    /**
     * C 引擎结果绑定
     * C engine result binding
     *
     * @return 绑定关系 binding
     */
    @Bean
    public Binding cEngineTaskResultBinding() {
        return BindingBuilder.bind(cEngineTaskResultQueue()).to(cEngineExchange()).with("c-engine.result.task-execute");
    }

    /**
     * Python AI 指令绑定
     * Python AI command binding
     *
     * @return 绑定关系 binding
     */
    @Bean
    public Binding pythonAiRiskAssessBinding() {
        return BindingBuilder.bind(pythonAiRiskAssessQueue()).to(pythonAiExchange()).with("python-ai.command.risk-assess");
    }

    /**
     * Python AI 结果绑定
     * Python AI result binding
     *
     * @return 绑定关系 binding
     */
    @Bean
    public Binding pythonAiRiskResultBinding() {
        return BindingBuilder.bind(pythonAiRiskResultQueue()).to(pythonAiExchange()).with("python-ai.result.risk-assess");
    }

    /**
     * 内部事件绑定(内部事件与领域事件共用交换机, 以路由键前缀区分)
     * Internal event binding (shares the domain exchange, distinguished by routing key prefix)
     *
     * @return 绑定关系 binding
     */
    @Bean
    public Binding internalEventBinding() {
        return BindingBuilder.bind(internalEventQueue()).to(domainEventExchange()).with(INTERNAL_EVENT_ROUTING_KEY);
    }

    /**
     * JSON 消息转换器
     * <p>
     * JSON Message Converter
     * <p>
     * 统一消息序列化: 领域事件/内部事件按事件类序列化, 跨语言契约按契约类序列化,
     * 字段命名需与 C/Python 端约定一致
     * <p>
     * Unified message serialization: domain/internal events are serialized by event class,
     * cross-language contracts by contract class. Field naming must match the contract
     * agreed with C/Python peers
     *
     * @return 消息转换器实例 message converter instance
     */
    @Bean
    public MessageConverter jacksonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    /**
     * 监听容器工厂
     * <p>
     * Rabbit Listener Container Factory
     * <p>
     * 使用 JSON 转换器并启用手动确认, 保证跨语言消息可靠投递
     * <p>
     * Uses the JSON converter with manual acknowledgement to guarantee
     * reliable delivery of cross-language messages
     *
     * @param connectionFactory 连接工厂 connection factory
     * @return 监听容器工厂 listener container factory
     */
    @Bean
    public SimpleRabbitListenerContainerFactory mq_ListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(jacksonMessageConverter());
        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        factory.setPrefetchCount(10);
        return factory;
    }

}
