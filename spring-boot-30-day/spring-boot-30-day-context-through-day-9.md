# Spring Boot 30-Day Practical — Complete Context Through Day 9

I am **Vijendra**. This is my hands-on Spring Boot 30-Day Practical Project.

My learning approach:

```text
WHY
↓
HOW
↓
Implementation
↓
Practical Experiment
↓
Observation
↓
Revision
```

Goal:

> **Interview-ready understanding + practical experience + concise God-Level notes.**

I want to **write the code myself**.

Do not give complete implementations unless I explicitly ask or I'm stuck.

When I explain something:
1. First evaluate whether my understanding is correct.
2. Correct/refine only what is necessary.
3. Challenge my understanding before explaining.
4. Prefer first-principles mental models over memorization.
5. Keep explanations concise but deep.

Jira workflow:

```text
To Do → In Progress → In Review → Done
```

---

# Project

**Project:** `spring-boot-30-day`

**Environment:**
- Java 25
- Maven 3.9.16
- IntelliJ IDEA
- MySQL 8
- HeidiSQL

**Database:** `spring_boot_30_day`

**Table:** `employees`

Current architecture:

```text
Spring Boot
↓
Spring Container
↓
EmployeeService
↓
EmployeeDAO
↓
JdbcTemplate
↓
DataSource
↓
HikariCP
↓
MySQL
```

---

# Day 1 — Raw JDBC ✅

Covered and implemented:
- JDBC fundamentals
- Driver / DriverManager
- Connection
- PreparedStatement
- SQL injection
- `executeQuery()`
- `executeUpdate()`
- ResultSet
- CRUD
- BigDecimal
- Generated keys
- JDBC transactions
- SQLException
- Resource management

Mental model:

```text
Java Application
↓
JDBC API
↓
JDBC Driver
↓
MySQL
```

Day 1 = **DONE**

---

# Day 2 — DataSource + HikariCP ✅

Covered and implemented:
- DataSource
- Connection pooling
- HikariCP
- Pool lifecycle
- Pool exhaustion
- `maximumPoolSize`
- `minimumIdle`
- `connectionTimeout`
- `idleTimeout`
- `maxLifetime`
- Connection leaks

Mental model:

```text
Application
↓
DataSource
↓
HikariCP Connection Pool
↓
JDBC Connection
↓
MySQL
```

Important understanding:

> The application doesn't necessarily create a brand-new physical DB connection for every operation. Hikari manages and reuses pooled connections.

Day 2 = **DONE**

---

# Day 3 — Spring JDBC + JdbcTemplate ✅

Covered and implemented:
- JdbcTemplate
- `query()`
- `queryForObject()`
- RowMapper
- `update()`
- INSERT / UPDATE / DELETE
- Parameter binding
- Generated keys
- KeyHolder
- Spring exception translation
- DataAccessException
- DAO architecture

Architecture:

```text
DAO
↓
JdbcTemplate
↓
DataSource
↓
HikariCP
↓
JDBC Driver
↓
MySQL
```

Important understanding:

> `JdbcTemplate` removes repetitive JDBC boilerplate and delegates connection/resource handling while integrating with Spring's infrastructure.

Day 3 = **DONE**

---

# Day 4 — Spring JDBC Transactions ✅

Covered and practically tested:
- Transactions
- ACID
- Raw JDBC transactions
- `PlatformTransactionManager`
- `DataSourceTransactionManager`
- `TransactionStatus`
- Manual transaction management
- Transaction boundaries
- `@Transactional` concept
- Rollback
- Propagation:
  - REQUIRED
  - REQUIRES_NEW
  - SUPPORTS
  - NOT_SUPPORTED
  - MANDATORY
  - NEVER
  - NESTED
- Isolation
- Dirty Read
- Non-Repeatable Read
- Phantom Read

Important:

At this point the application wasn't fully Spring-managed, so proper `@Transactional` proxy behavior was postponed.

Day 4 = **DONE**

---

# Day 5 — Spring JDBC Exception Handling ✅

Covered and practically tested:
- SQLException vs DataAccessException
- Spring exception translation
- DataAccessException hierarchy
- DuplicateKeyException
- EmptyResultDataAccessException
- IncorrectResultSizeDataAccessException
- BadSqlGrammarException
- DAO vs Service responsibility
- Catch vs propagate
- Rollback + rethrow
- Refactoring exception handling

Important practical lesson:

> If a transactional exception is caught and swallowed inside the target method, the transaction interceptor may see normal method completion and commit.

Day 5 = **DONE**

---

# Day 6 — Convert JDBC Application to Spring-Managed Beans ✅

Converted manually wired application into Spring-managed application.

### Before

```text
Main
↓
new EmployeeService(...)
↓
new EmployeeDAO(...)
↓
new JdbcTemplate(...)
```

### After

```text
Spring Container
↓
EmployeeService
↓
EmployeeDAO
↓
JdbcTemplate
↓
DataSource
↓
HikariCP
↓
MySQL
```

Practically applied:
- Spring ApplicationContext
- Spring-managed beans
- `@Repository`
- `@Service`
- Constructor injection
- Dependency injection
- Removed unnecessary manual `new`
- Spring-managed object graph

Important understanding:

> Spring now owns the lifecycle and dependency graph of the application objects.

Day 6 = **DONE**

---

# Day 7 — Spring Boot + Auto Configuration ✅

Covered practically:
- Spring Boot application startup
- `@SpringBootApplication`
- Component scanning
- Auto-configuration
- Spring Boot application context
- Boot-managed application startup
- How Boot builds application infrastructure

Important mental model:

```text
Spring Boot Application
↓
ApplicationContext
↓
Component Scanning
+
Auto Configuration
↓
Infrastructure + Application Beans
```

Day 7 = **DONE**

---

# Day 8 — Spring AOP + Proxies ✅

Studied deeply and experimentally.

## AOP Fundamentals

- Cross-cutting concerns
- Code scattering
- Code tangling
- AOP as separation of cross-cutting concerns

## AOP Terminology

- Aspect
- Join Point
- Pointcut
- Advice
- Target
- Proxy

Mental model:

```text
Caller
↓
Spring AOP Proxy
↓
Advice
↓
Target Object
```

## Advice

Experimented with:
- `@Before`
- `@After`
- `@AfterReturning`
- `@AfterThrowing`
- `@Around`

Important experimentally verified:

```text
@Around
↓
proceed()
↓
Target executes
```

If `proceed()` is NOT called, target does not execute.

## JoinPoint

Experimented with:
- `getTarget()`
- `getThis()`
- `getSignature()`
- `getArgs()`

## Pointcut Designators

Practically tested:

```text
execution(...)   → method execution/signature
within(...)      → type/package boundary
args(...)        → runtime argument types
@annotation(...) → annotation on method
@within(...)     → annotation on class/type
@target(...)     → annotation on runtime target type
bean(...)        → Spring bean identity/name
```

## Proxy Types

Experimentally verified both.

### JDK Dynamic Proxy

Example:

```text
jdk.proxy2.$Proxy52
```

Interface-based.

### Class-based / CGLIB-style proxy

Example:

```text
StripePaymentGateway$$SpringCGLIB$$0
```

Subclass-based.

Important:

> Having an interface does NOT automatically mean Spring will use a JDK proxy.

Tested:

```properties
spring.aop.proxy-target-class=false
```

and observed JDK Dynamic Proxy.

## Self Invocation

Experimentally understood:

External call:

```text
Caller
↓
Proxy
↓
methodA()
↓
Advice
```

But:

```text
Target
↓
this.methodB()
↓
Target directly
```

Proxy is bypassed.

Therefore proxy-based features can be bypassed during self-invocation.

Relevant to:
- `@Transactional`
- `@Async`
- `@Cacheable`
- Custom AOP

## Final Day 8 Mental Model

```text
Spring Container
↓
Spring Bean
↓
AOP applicable?
↓
YES
↓
Create Proxy
↓
Caller
↓
Proxy
↓
Pointcut
↓
Advice
↓
Target Method
```

Day 8 = **DONE**

---

# Day 9 — Spring Transaction Management 🚧 IN PROGRESS

## Day 9 Objective

Connect:

```text
Day 4 — Transactions
        +
Day 8 — AOP + Proxies
        ↓
Day 9 — Spring Transaction Management
```

Core question:

> **Why does `@Transactional` actually create a transaction when placed on a Spring-managed service method?**

Mental model being built:

```text
@Transactional
↓
Transactional metadata
↓
Spring transaction infrastructure
↓
Transaction Advisor
↓
TransactionInterceptor
↓
Proxy
↓
Transaction starts
↓
Target method executes
↓
Commit / Rollback
```

---

# Day 9 Experiment 1 — Baseline ✅

Started with a normal Spring-managed service method without `@Transactional`.

Checked:

```java
System.out.println(employeeService.getClass());
```

Observed:

```text
com.vijendra.service.EmployeeService
```

This proved the caller was interacting with the actual service class.

Baseline:

```text
Caller
↓
EmployeeService
↓
method()
```

Important:

> Spring-managed bean does NOT automatically mean proxy.

---

# Day 9 Experiment 2 — Add `@Transactional` ✅

Added:

```java
@Transactional
public void testTransaction() {
    System.out.println("Inside service");
}
```

Checked:

```java
employeeService.getClass()
```

Observed:

```text
com.vijendra.service.EmployeeService$$SpringCGLIB$$0
```

This experimentally proved that adding transactional behavior caused Spring to expose a CGLIB proxy.

Flow:

```text
Caller
↓
EmployeeService$$SpringCGLIB$$0
↓
EmployeeService target
```

Important:

> `@Transactional` itself is metadata. Spring's transaction infrastructure uses that metadata to apply transactional interception.

---

# Day 9 Experiment 3 — Transaction Interceptor Logging ✅

Added:

```properties
logging.level.org.springframework.transaction.interceptor=TRACE
```

Observed:

```text
TransactionInterceptor :
Getting transaction for [com.vijendra.service.EmployeeService.testTransaction]

Inside service

TransactionInterceptor :
Completing transaction for [com.vijendra.service.EmployeeService.testTransaction]
```

This experimentally proved:

```text
Caller
↓
CGLIB Proxy
↓
TransactionInterceptor
↓
Target method
↓
Transaction completion
```

Important:

> Interception happens before the target method and completion happens afterward.

---

# Day 9 Experiment 4 — RuntimeException → Rollback ✅

Created a transactional service method that:

```text
UPDATE employee 1
↓
UPDATE employee 2
↓
throw RuntimeException
```

Observed:

```text
Updating Employee with Id : 1
Updating Employee with Id : 2

TransactionInterceptor :
Completing transaction ... after exception:
java.lang.RuntimeException
```

After execution:

> Both DB records remained unchanged.

Therefore experimentally verified:

```text
@Transactional
↓
BEGIN
↓
UPDATE 1
↓
UPDATE 2
↓
RuntimeException
↓
TransactionInterceptor sees exception
↓
ROLLBACK
```

Important distinction:

> SQL execution does not mean the changes are permanently committed.

Both updates executed, but the transaction was rolled back.

---

# Day 9 Experiment 5 — Swallow RuntimeException ✅

Modified the target method so it caught the `RuntimeException` internally.

Observed:

> Both DB updates were committed.

Reason:

```text
Target method
↓
RuntimeException
↓
caught inside target
↓
exception swallowed
↓
target returns normally
↓
TransactionInterceptor sees normal completion
↓
COMMIT
```

Important experimentally verified principle:

> The transaction interceptor needs the exception to reach the proxy boundary so that it can apply its rollback rules.

If the target catches and swallows the exception, the interceptor sees successful method completion.

This reinforced the Day 5 lesson:

> Catch + swallow can produce incorrect transactional behavior.

---

# Day 9 Experiment 6 — Checked Exception Default Behavior ✅

Changed the exception to:

```java
throw new Exception("Something went wrong");
```

and allowed it to escape the transactional method.

Observed:

> Both records were updated.

Therefore:

```text
@Transactional
↓
checked Exception escapes
↓
TransactionInterceptor sees it
↓
default rollback rules
↓
NO rollback
↓
COMMIT
```

Important default behavior experimentally verified:

```text
RuntimeException → rollback by default
Error            → rollback by default
Checked Exception → NO rollback by default
```

---

# Day 9 Experiment 7 — `rollbackFor` ✅

Changed the annotation to:

```java
@Transactional(rollbackFor = Exception.class)
```

and threw:

```java
throw new Exception("Something went wrong");
```

Observed:

> Transaction rolled back as expected.

Mental model:

```text
@Transactional
+
rollbackFor = Exception.class
↓
checked exception becomes rollback-triggering
```

Important:

> `rollbackFor` does NOT create the transaction.

`@Transactional` + transaction infrastructure creates the transactional interception.

`rollbackFor` changes the rollback decision.

---

# Day 9 Transaction Boundary ✅

Discussed:

```java
@Transactional
public void testTransaction() {
    updateEmployee(1);
    updateEmployee(2);
    updateEmployee(3);
}
```

Correct understanding:

> `@Transactional` does NOT mean every SQL query gets its own transaction.

Instead:

```text
Proxy
↓
BEGIN
↓
testTransaction()
 ├── DAO → UPDATE
 ├── DAO → UPDATE
 └── DAO → UPDATE
↓
COMMIT
```

So:

> **The transaction boundary normally surrounds the proxied service method invocation.**

Multiple DAO operations can participate in the same transaction.

Important refinement:

> The DAO operations execute using the transaction context established around the service method.

---

# Day 9 — Service Layer Transaction Boundary

Discussed why service/business layer is usually the natural place for transaction boundaries.

Example:

```text
@Transactional
placeOrder()
    ↓
Save Order
    ↓
Save Order Items
    ↓
Reduce Inventory
    ↓
Record Payment
```

The business operation is treated as one unit of work.

Mental model:

```text
@Transactional
↓
Business Operation
↓
ONE Transaction
```

Not:

```text
DAO 1 → transaction
DAO 2 → transaction
DAO 3 → transaction
```

---

# Day 9 — REQUIRED Understanding ✅

Started the practical propagation section.

Scenario:

```text
EmployeeService
@Transactional
    ↓
AnotherService
@Transactional
```

Both use default:

```java
Propagation.REQUIRED
```

Understanding:

> `REQUIRED` joins an existing transaction if one already exists. It creates a new transaction only when no transaction exists.

Conceptual flow:

```text
EmployeeService.methodA()
@Transactional
↓
Proxy A
↓
BEGIN Transaction A
↓
AnotherService.methodB()
@Transactional(REQUIRED)
↓
Proxy B
↓
Existing Transaction A found
↓
JOIN Transaction A
↓
methodB()
↓
methodA()
↓
COMMIT Transaction A
```

Important refinement:

`methodB()` can still be intercepted by **AnotherService's proxy**.

The second proxy doesn't necessarily create a second transaction. Its transaction interceptor sees that a transaction already exists and participates in it.

Current understanding:

> `REQUIRED` = **join existing transaction, otherwise create one.**

---

# CURRENT POSITION — DAY 9 NOT DONE

Completed:

- Baseline without transaction ✅
- `@Transactional` proxy behavior ✅
- CGLIB proxy observation ✅
- TransactionInterceptor observation ✅
- RuntimeException rollback ✅
- Swallowed exception → commit ✅
- Checked exception → commit by default ✅
- `rollbackFor` → checked exception rollback ✅
- Transaction boundary understanding ✅
- Service-layer boundary understanding ✅
- REQUIRED conceptual understanding ✅

Current next step:

> **Practically implement and verify REQUIRED.**

---

# Remaining Day 9 Roadmap

## Experiment 8 — REQUIRED 🚧 NEXT

Create:

```text
EmployeeService
    ↓
AnotherService
```

Both methods have `@Transactional`.

First run without exceptions and inspect transaction logs.

Then introduce an exception and observe that both participate in the **same transaction**.

Expected:

```text
Service A
@Transactional
↓
Transaction A
↓
Service B
@Transactional(REQUIRED)
↓
Join Transaction A
↓
ONE physical transaction
```

Then test failure behavior.

---

## Experiment 9 — REQUIRES_NEW

Verify:

```text
Service A
@Transactional
↓
Service B
@Transactional(REQUIRES_NEW)
```

Expected:

```text
Transaction A
↓
A suspended
↓
Transaction B starts
↓
B commits/rolls back independently
↓
A resumes
↓
A continues
```

Must be experimentally verified, not just memorized.

---

## Experiment 10 — Self Invocation

Test:

```java
public void methodA() {
    this.methodB();
}

@Transactional
public void methodB() {
    ...
}
```

Expected:

```text
External call
↓
Proxy
↓
methodB()
↓
@Transactional works
```

But:

```text
methodA()
↓
this.methodB()
↓
Target directly
↓
Proxy bypassed
↓
@Transactional interception bypassed
```

Must explicitly connect to Day 8 self-invocation.

---

## Experiment 11 — Cross-Service Proxy

Verify:

```text
EmployeeService
↓
AnotherService proxy
↓
TransactionInterceptor
↓
AnotherService target
```

This reinforces why transactional behavior works when crossing Spring proxy boundaries.

---

# What I Should Be Able to Explain by the End of Day 9

## 1. What does `@Transactional` actually do?

It provides transactional metadata that Spring's transaction infrastructure uses to apply transactional interception around a Spring-managed bean method.

## 2. Why does it need Spring-managed/proxied beans?

Because Spring needs to intercept the method invocation.

```text
Caller
↓
Proxy
↓
TransactionInterceptor
↓
Target
```

## 3. Actual transaction flow

```text
Caller
↓
Spring Proxy
↓
TransactionInterceptor
↓
BEGIN
↓
Target Method
↓
DAO
↓
Database
↓
COMMIT / ROLLBACK
```

## 4. RuntimeException

Default:

```text
RuntimeException
↓
ROLLBACK
```

## 5. Checked Exception

Default:

```text
Checked Exception
↓
COMMIT
```

unless rollback rules are changed.

## 6. `rollbackFor`

```java
@Transactional(rollbackFor = Exception.class)
```

changes rollback rules so the checked exception can cause rollback.

## 7. Transaction boundary

```text
@Transactional method
↓
ONE transaction boundary
↓
multiple DB operations can participate
```

## 8. REQUIRED

```text
Existing transaction?
YES → join it
NO  → create one
```

## 9. REQUIRES_NEW

```text
Existing transaction
↓
suspend it
↓
start independent transaction
```

## 10. Self invocation

```text
this.method()
↓
proxy bypassed
↓
transactional interception can be bypassed
```

## 11. Service-layer boundary

Transactions normally belong around a **business operation**, making the service layer a natural transaction boundary.

---

# Final Day 9 Mental Model

```text
                 Spring Container
                       ↓
                 EmployeeService
                       ↓
              AOP / Transaction Proxy
                       ↓
              TransactionInterceptor
                       ↓
                 BEGIN TX
                       ↓
             EmployeeService Method
                       ↓
                  EmployeeDAO
                       ↓
                 JdbcTemplate
                       ↓
                    Database
                       ↓
              Success / Exception
                  ↙          ↘
              COMMIT       ROLLBACK
```

And the bigger picture:

```text
DAY 4
Transactions
    +
DAY 8
AOP + Proxies
    ↓
DAY 9
Spring Transaction Management
```

---

# Important Teaching Rules for Continuation

Do NOT restart:
- JDBC
- DataSource
- HikariCP
- JdbcTemplate
- ACID
- Isolation
- transaction fundamentals
- IoC/DI
- Bean lifecycle
- AOP basics
- proxy basics
- pointcut designators

Do NOT turn Day 9 into a generic transaction theory lecture.

Do NOT dump all remaining Day 9 theory at once.

Teach interactively:

```text
WHY
↓
Small concept
↓
HOW
↓
Implementation
↓
Experiment
↓
Observation
↓
Explanation
↓
Next
```

Challenge my understanding before explaining.

Let me write the code.

If I provide an explanation, first evaluate it, then refine only what's necessary.

**Continue from the practical REQUIRED experiment.**
