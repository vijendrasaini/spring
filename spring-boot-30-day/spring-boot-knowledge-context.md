# Spring Boot Knowledge Context

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

Before starting the next day:

> Write **God-Level notes** in the notebook. To the point. Precise. Noise-free. No repetition. Only what matters.

Do not jump to the next day until those notes exist.

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

How a day starts:

1. I get a **Jira title + Jira description** for that day.
2. I create the Jira ticket.
3. I say **Done / created**.
4. Only then we start the day, topic by topic.

Do not start teaching a new day before the Jira ticket exists.

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

# Day 9 — Spring Transaction Management ✅

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

# Day 9 Experiment 8 — REQUIRED ✅

Practically verified with:

```text
EmployeeService.updateName()     @Transactional (REQUIRED)
        ↓
StripePaymentGateway.pay()       @Transactional (REQUIRED)
```

B's interceptor still runs. It does **not** skip interception. It sees an existing transaction and **joins** it.

If A throws `RuntimeException` after B returns:

```text
ONE physical transaction
↓
A UPDATE + B UPDATE
↓
A exception
↓
ROLLBACK both
```

Employees 1 and 4 both remain unchanged.

---

# Day 9 Experiment 9 — REQUIRES_NEW ✅

If B uses `Propagation.REQUIRES_NEW`:

```text
Transaction A starts
↓
A suspended
↓
Transaction B starts
↓
B UPDATE
↓
B COMMITS (independent, durable)
↓
A resumes
↓
A throws RuntimeException
↓
only Transaction A rolls back
```

Employee 4 (B) remains updated. Employee 1 (A) is rolled back.

Important:

> `REQUIRES_NEW` is independent because A is suspended, B commits before control returns to A, then A resumes.

---

# Day 9 — MANDATORY ✅

Practiced:

```java
@Transactional(propagation = Propagation.MANDATORY)
```

If an outer transaction exists → join it, then `pay()` executes.

If no outer transaction exists:

```text
Caller
↓
B proxy
↓
TransactionInterceptor
↓
IllegalTransactionStateException
↓
target.pay() NEVER runs
```

The interceptor throws **before** the target method. Not a single line of `pay()` executes.

> `MANDATORY` = a transaction must already exist. Join it. Do not create one.

Other propagation types were also practiced during this day.

---

# Day 9 Experiment 10 — Self Invocation ✅

Connected to Day 8.

If a non-transactional method calls `this.testTransaction()`:

```text
methodA()
↓
this.testTransaction()
↓
Target directly
↓
Proxy bypassed
↓
TransactionInterceptor never runs
↓
NO transaction
```

`@Transactional` on the inner method does nothing for that call.

---

# Day 9 Experiment 11 — Cross-Service Proxy ✅

Verified with injected `PaymentGateway`:

```text
EmployeeService proxy
        ↓
paymentGateway.pay(...)
        ↓
StripePaymentGateway proxy
        ↓
TransactionInterceptor
        ↓
target.pay()
```

Transactional metadata on B works because the call crosses a **Spring bean proxy boundary**, not `this`.

---

# CURRENT POSITION — DAY 9 DONE ✅

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
- REQUIRED ✅
- REQUIRES_NEW ✅
- MANDATORY ✅
- Other propagation types practiced ✅
- Self invocation ✅
- Cross-service proxy ✅

Day 9 = **DONE**

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

# Day 9 — God-Level Notes (Notebook)

## What `@Transactional` is

`@Transactional` is only a **label** on a method or class. A label is called metadata. It does not open a database transaction by itself.

Spring reads this label. If the class is a Spring bean, Spring may wrap that bean with a **proxy**.

**Proxy** = a wrapper object that looks like the real bean. Callers talk to the wrapper. The wrapper can run extra work before and after the real method.

**Target** = the real object behind the proxy (the actual `EmployeeService` instance).

**TransactionInterceptor** = Spring's extra work on that proxy. It starts a transaction, lets the real method run, then commit or rollback.

```text
Caller
↓
Proxy (wrapper)
↓
TransactionInterceptor
↓
start or join a transaction
↓
Target method (real code)
↓
DAO → database
↓
commit or rollback
```

A bean in the Spring container is **not** always a proxy. If there is no `@Transactional` (and no other AOP advice), the caller gets the real class. No wrapper. No transaction from Spring.

---

## Rollback rules

When the method finishes, the interceptor decides commit or rollback.

Default:

- `RuntimeException` or `Error` leaves the method → **rollback**
- Checked `Exception` (like `throw new Exception(...)`) leaves the method → **commit**

`rollbackFor = Exception.class` changes that rule so a checked exception also causes rollback. It does not create the transaction. It only changes the decision.

**Swallowing an exception** = catch it inside the method and do not rethrow it.

If you swallow it, the interceptor thinks the method completed normally → **commit**.  
Spring can rollback only if the exception reaches the proxy.

---

## Transaction boundary

A transaction boundary is **where the transaction starts and ends**.

With `@Transactional`, that place is the **proxied service method**, not each SQL statement.

```text
@Transactional
placeOrder()
    save order
    save items
    reduce stock
↓
ONE transaction around the whole method
```

Many DAO calls can run inside that one transaction. Either all become permanent, or none do.

Put `@Transactional` on the **service**, because the service is the business operation.

Do not put it on the DAO (one SQL is not the business unit).  
Do not put it on the controller (HTTP adapter is not the business unit).

---

## Propagation

**Propagation** = what this method should do if a transaction already exists.

### REQUIRED (default)

If a transaction already exists → **join it** (use the same one).  
If none exists → **create** a new one.

Join does not mean B has no interceptor. B's proxy still runs. B's interceptor sees A's transaction and participates in it.

So two interceptors can still mean **one** real database transaction.

If A updates employee 1, B updates employee 4, then A throws after B returns → both changes rollback.

### REQUIRES_NEW

B does not join A.

```text
A's transaction is paused (suspended)
↓
B starts its own transaction
↓
B commits or rollbacks by itself
↓
A's transaction continues
```

If B already committed, and then A fails, B's database changes stay. A's changes rollback.

### MANDATORY

A transaction **must already exist**.

If it exists → join it.  
If it does not exist → the interceptor throws. The real `pay()` method does not run at all. Not even the first line.

---

## Self-invocation

**Self-invocation** = an object calling **its own** method using `this`.

Example:

```java
public void methodA() {
    this.methodB();   // self-invocation
}

@Transactional
public void methodB() { ... }
```

`this` is the real target object, not the proxy.

```text
methodA()
↓
this.methodB()
↓
goes straight to the real method
↓
proxy is skipped
↓
TransactionInterceptor does not run
↓
no Spring transaction from methodB
```

`@Transactional` works only when the call goes **through the proxy**.  
An outside caller (`employeeService.methodB()`) goes through the proxy.  
`this.methodB()` does not.

This is the same Day 8 proxy rule. It also applies to `@Transactional`, `@Async`, `@Cacheable`.

---

## Cross-service proxy

**Cross-service proxy** = one Spring bean calling a **different** Spring bean that was injected.

Example: `EmployeeService` calls `paymentGateway.pay(...)`.

`paymentGateway` is not `this`. It is another bean given by Spring. Spring gives the **proxy** of `StripePaymentGateway`.

```text
EmployeeService
↓
paymentGateway.pay(...)     // call to another bean
↓
StripePaymentGateway proxy
↓
TransactionInterceptor
↓
real pay() method
```

That is why `@Transactional` on `pay()` can work. The call crosses a proxy boundary.

Difference:

```text
this.method()              → same object, proxy skipped
otherBean.method()         → other bean's proxy is used
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
- `@Transactional` internals
- propagation (REQUIRED / REQUIRES_NEW / MANDATORY)
- self-invocation / proxy bypass

Do NOT dump Day 10 theory at once.

Do NOT start the next day until God-Level notebook notes for the finished day are written.

Do NOT start a new day's topics until the Jira ticket for that day is created and I say Done / created.

God-Level notes style:
- Simple language
- Define important terms
- Enough to revise later without the chat
- No repetition, no lecture dump

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

---

# Day 10 — Spring Web / REST 🚧 IN PROGRESS

## Day 10 Objective

Connect:

```text
Day 7 — Spring Boot
        +
Day 9 — Transaction on the service
        ↓
Day 10 — Spring Web / REST
```

The service layer is ready. The app still has no way to receive an HTTP request.

Current:

```text
main()
↓
ApplicationContext
↓
EmployeeService
↓
EmployeeDAO
↓
MySQL
```

Needed:

```text
HTTP Request
↓
DispatcherServlet
↓
Controller
↓
EmployeeService (@Transactional)
↓
EmployeeDAO
↓
MySQL
↓
HTTP Response
```

Core question:

> **Why does this application need a web layer, and what actually happens between an HTTP request and EmployeeService?**

Mental model being built:

```text
Client (browser / Postman)
↓
HTTP
↓
Embedded Web Server
↓
DispatcherServlet
↓
Controller
↓
EmployeeService
↓
DAO
↓
Database
```

Transaction boundary stays on the **service**. Already decided. Do not move it to the controller.

---

# Day 10 Experiment 1 — Baseline ✅

Observed from current app (no web starter, work starts from `main()`):

1. Postman **cannot** call this app. Nothing is listening on a port. There is no web server (Tomcat). The app is not open to HTTP.
2. After `updateName()` finishes, `main()` has nothing left to do. There is no server thread keeping the JVM alive, so the process ends.
3. A controller will be the HTTP entry point. It maps a route (URL + HTTP method) to a Java method, which then calls `EmployeeService`. It does not own the business operation or the transaction.

---

# Day 10 Experiment 2 — Add the web starter 🚧 NEXT

Add `spring-boot-starter-web`. Observe startup. Do not create a controller yet.


