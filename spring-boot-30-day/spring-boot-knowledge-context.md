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
- `spring-boot-starter` vs `spring-boot-starter-web`
- DispatcherServlet / `@RestController` / CRUD URLs
- `@PathVariable` / `@RequestBody`
- `@ExceptionHandler` / `@RestControllerAdvice`
- EmptyResult → 404 / type mismatch → 400
- Bean Validation / `@Valid` / `MethodArgumentNotValidException`
- `@NotBlank` `@Size` `@Email` `@Pattern` / field error map
- Request/Response DTO vs domain model
- `ResponseEntity` / 201 / 204
- JPA vs Hibernate vs Spring Data JPA
- `@Entity` / `JpaRepository` / `Optional` findById / `save`

Do NOT dump Day 15 theory at once.

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

# Day 10 — Spring Web / REST ✅

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

# Day 10 Experiment 2 — Add the web starter ✅

Added `spring-boot-starter-web`.

Observed:

```text
Tomcat initialized with port 8080 (http)
Tomcat started on port 8080 (http) with context path '/'
Started SpringBoot30DayApplication
```

The process stays running. Embedded Tomcat is listening. That is why the JVM does not exit.

`main()` should no longer call `EmployeeService` directly. HTTP should trigger the service later.

---

# Day 10 Experiment 3 — Hit the app with no controller ✅

Hit `http://localhost:8080` → **404**.

Tomcat received the HTTP request. There was no route mapped to `/`, so Spring had no Java method to call. `EmployeeService` was never reached.

404 here means: server is up, **handler method is missing**.

---

# Day 10 Experiment 4 — First controller ✅

Created `TestController`:

```java
@RestController
@GetMapping("/")
public String index() { ... }
```

Chrome `GET /` showed the string. Mapping exists. HTTP reached a Java method.

Flow proved:

```text
Browser
↓
Tomcat :8080
↓
DispatcherServlet
↓
TestController.index()
↓
HTTP response body
```

---

# Day 10 Experiment 5 — Controller → EmployeeService ✅

`TestController` injects `EmployeeService` through the constructor.

Mapped:

- `GET /` → string body
- `GET /employees` → `employeeService.getAllEmployees()` → `List<Employee>`

Controller stays thin. No `@Transactional` on the controller.

Flow proved:

```text
HTTP GET /employees
↓
DispatcherServlet
↓
TestController.getAllEmployees()
↓
EmployeeService
↓
EmployeeDAO
↓
MySQL
↓
JSON response body
```

Returning `List<Employee>` becomes JSON because `@RestController` writes the return value as the body, and **Jackson** (from `starter-web`) converts Java objects to JSON.

---

# Day 10 Experiment 6 — GET one employee (`@PathVariable`) ✅

Mapped on `EmployeeController`:

- `GET /employees` → list
- `GET /employees/{id}` → one employee

Both tested and working when the row exists.

The mapping **works**. Earlier 500 was not a missing route.

`404` = DispatcherServlet found no handler.
`500` = handler ran, then an exception escaped.

Empty table + `queryForObject` → `EmptyResultDataAccessException` → HTTP 500.

The exception is in the **server log**. The HTTP JSON body hides the Java exception name by default.

"No row" should later become HTTP **404**, not 500. That is a later experiment.

---

# Day 10 Experiment 7 — POST + `@RequestBody` ✅

`POST /employee/create` with `@RequestBody Employee` works.

`@RequestBody` = read JSON body → Jackson → Java object → method argument.

**405 lesson:** curl was `POST /employees/create`. That path matches `GET /employees/{id}` with `{id} = "create"`. Only GET is allowed on that pattern → **405**, not a missing POST method in general.

The mapped path was `POST /employee/create` (singular). URL and mapping must be the same string.

---

# Day 10 Experiment 8 — CRUD routes + class `@RequestMapping` ✅

`@RequestMapping("/employees")` on the class. Methods add only the rest.

Tested via Postman:

```text
GET    /employees
GET    /employees/{id}
POST   /employees
PATCH  /employees/{id}    (name only — partial update)
DELETE /employees/{id}
```

URL = noun. HTTP method = verb. No `/create` in the path.

PATCH (some fields) is not PUT (replace the whole resource). Current update only changes name, so PATCH fits.

---

# Day 10 Experiment 9 — Missing employee should be 404 ⏸ LATER

Parked. `@ExceptionHandler` / `@ControllerAdvice` not studied yet.

Until then: no row + `queryForObject` → `EmptyResultDataAccessException` → HTTP **500**. That is Spring's default, not a wrong mapping.

Day 10 core = **DONE**. Exception handlers = later.

---

# Day 10 — God-Level Notes (Notebook)

## Why a web layer

Before Day 10, `main()` called `EmployeeService`. Only your process could use the app.

A **web layer** opens an HTTP door so Postman, a browser, or a mobile app can call the same service.

`main()` only **starts** the app. It does not call the service.

`@Transactional` stays on the **service**. The controller is HTTP only.

---

## Starters

A **starter** is a Maven pack: one dependency pulls a set of libraries.

**`spring-boot-starter`** = Boot container, auto-config, logging. No HTTP. No port.

**`spring-boot-starter-web`** = that core pack, plus:

- **Embedded Tomcat** = a web server that runs **inside** your JVM. It opens a port (usually 8080) and keeps the process alive.
- **Spring MVC** = maps HTTP (path + method) to a Java method.
- **Jackson** = Java object ↔ JSON.

```text
starter        = Boot app, no HTTP door
starter-web    = Boot app + HTTP door + server that stays running
```

---

## Request flow

**DispatcherServlet** = Spring MVC’s front door. Every HTTP request goes there first. It finds the matching controller method and calls it.

```text
Client (Postman / browser)
↓
HTTP
↓
Embedded Tomcat :8080
↓
DispatcherServlet
↓
Controller
↓
EmployeeService
↓
DAO / JdbcTemplate
↓
MySQL
↓
Java object
↓
Jackson JSON
↓
HTTP response
```

---

## Controller annotations

**`@RestController`** = this class is a Spring bean that handles HTTP, and the **return value is the HTTP body** (not a page name).

- Return `String` → that string is the body.
- Return `Employee` or `List<Employee>` → Jackson turns it into JSON.

**`@RequestMapping("/employees")`** on the **class** = every method path starts with `/employees`.

**`@GetMapping` / `@PostMapping` / `@PatchMapping` / `@DeleteMapping`** = this method handles that HTTP method + path.

They only **register a mapping**. They do not talk to the database and they do not start a transaction.

---

## Path variable vs request body

A **path variable** is a **data** piece inside the URL.

```text
GET /employees/1
         |        |
         noun     data (id = 1)
```

`{id}` is a placeholder. **`@PathVariable`** copies that piece into a method argument. The URL is text, so `"1"` is converted to `int`.

A **request body** is the JSON (or other payload) **after** the headers. Used when the client **sends an object**, usually on POST/PATCH/PUT.

**`@RequestBody`** = read the body → Jackson → Java object → method argument.

```text
GET     data in the URL      @PathVariable
POST    data in the JSON     @RequestBody
```

`@PathVariable` is not `@RequestParam`.  
`/employees/1` = path variable.  
`/employees?id=1` = query parameter (`@RequestParam`). We used the first style.

---

## CRUD URLs

The URL is the **noun** (resource), usually plural. The HTTP method is the **verb**. Do not put `create` / `get` / `delete` in the path.

```text
GET    /employees         list
GET    /employees/{id}    one
POST   /employees         create
PATCH  /employees/{id}    change some fields
PUT    /employees/{id}    replace the whole resource
DELETE /employees/{id}    delete
```

**PATCH** = some fields. **PUT** = whole object. Name-only update → PATCH.

Do not use `/employees/create`. `{id}` will treat `create` as an id.

---

## Status codes we hit

**404** (no mapping) = Tomcat is up. DispatcherServlet found **no** Java method for that URL.

**405 Method Not Allowed** = this **path pattern** exists, but not for this HTTP method.

Example: `POST /employees/create` matched `GET /employees/{id}` with `id = "create"`. Only GET was allowed → 405.

**500** = a method **was** found and it ran. Then an exception escaped.

Example: no row for that id → `queryForObject` needs exactly one row → `EmptyResultDataAccessException` → 500.

The exception **is** thrown. It is in the **server log**. The HTTP JSON is a generic `"Internal Server Error"` because Spring hides Java exception names from the client by default.

Missing employee **should** be 404, not 500. That mapping (exception handler) is **later**.

---

## Controller vs service

```text
Controller  = HTTP door (route, path variable, body, JSON)
Service     = business operation and transaction boundary
DAO         = SQL
```

The controller injects the service (constructor). It does not contain JDBC. It does not get `@Transactional`.

---

# Day 11 — Web Exception Handling ✅

## Day 11 Objective

Connect:

```text
Day 5 — DataAccessException
        +
Day 10 — HTTP / REST
        ↓
Day 11 — Web exception handling
```

Day 10 parked this: missing employee → `EmptyResultDataAccessException` → HTTP **500**.

Wrong meaning. The route worked. The resource is missing. That should be **404**.

Core question:

> **How does the web layer turn a Java exception into the right HTTP status and a readable body, without leaking internals?**

Mental model:

```text
Controller / Service / DAO
↓
Java exception
↓
@ExceptionHandler / @ControllerAdvice
↓
HTTP status + simple JSON
```

Jira ticket created.

---

# Day 11 Experiment 1 — Baseline ✅

`GET /employees/{missing-id}` → HTTP **500**.

Understood:

- 500 means “server error” to the client. That meaning is wrong here. The server is fine. The employee does not exist.
- The **route did not fail**. DispatcherServlet found `getEmployee`.
- The controller method **did run**. Then DAO `queryForObject` threw `EmptyResultDataAccessException`. That exception was not translated to HTTP by us, so Boot defaulted to 500.
- Turning Java exception → HTTP status is the **web layer’s** job (controller / `@ExceptionHandler`), not the DAO and not the service.

---

# Day 11 Experiment 2 — First `@ExceptionHandler` ✅

Added `@ExceptionHandler(EmptyResultDataAccessException.class)` on `EmployeeController`.

Observed: missing id → HTTP **404** + simple string body.

`getEmployee` stays without try/catch. Exception bubbles up. Handler translates to HTTP.

---

# Day 11 Experiment 3 — Scope of controller `@ExceptionHandler` ✅

Controller-level `@ExceptionHandler` only covers that controller’s requests.

Copying the same handler into every controller is a design smell (duplication, painful changes).

---

# Day 11 Experiment 4 — `@ControllerAdvice` ✅

Moved handler to `GlobalExceptionHandler` with `@RestControllerAdvice`.
Removed from `EmployeeController`.

Missing id still → **404** + message. One place for the whole app.

---

# Day 11 Experiment 5 — Simple error JSON body ✅

`ErrorResponse` with `status` + `message`. Handler returns `ResponseEntity<ErrorResponse>`.

Missing id → **404** + structured JSON. No Java exception class name in the body.

---

# Day 11 Experiment 6 — Invalid path variable → 400 ✅

`GET /employees/abc` → `MethodArgumentTypeMismatchException`.

Spring’s default was already **400** (unlike empty row → 500). Custom `@ExceptionHandler` still useful: same `ErrorResponse` shape for every API error.

```text
EmptyResultDataAccessException      → 404  (valid id, no row)
MethodArgumentTypeMismatchException → 400  (id not convertible to int)
```

---

# Day 11 CURRENT POSITION — CORE DONE ✅

- Baseline missing row → 500 (wrong meaning) ✅
- `@ExceptionHandler` on controller ✅
- `@RestControllerAdvice` global ✅
- Empty row → 404 + `ErrorResponse` ✅
- Bad path type → 400 + `ErrorResponse` ✅

Ready for day review → God-level notes → Jira Done.

Day 11 = **DONE**

---

# Day 11 — God-Level Notes (Notebook)

## Why this day

Day 10 made HTTP work. Missing employee still became HTTP **500**.

500 means “server broke.” Here the server is fine. The resource is missing. That should be **404**.

Day 5’s exception is still useful inside Java. Day 11 teaches the **web layer** to turn that exception into the right HTTP status and a simple body.

```text
Day 5  — exception in DAO/service
Day 10 — HTTP door
Day 11 — exception → HTTP status + readable JSON
```

---

## Layer rule

```text
DAO / Service  → throw or propagate the exception
Web layer      → translate it to HTTP
```

Do **not** catch-and-hide inside `getEmployee` just to return a string.  
Do **not** put HTTP status logic in the DAO.

Controller methods stay clean. No try/catch required for this pattern.

---

## What happens without a handler

```text
GET /employees/99999
↓
route matches, getEmployee runs
↓
queryForObject → 0 rows
↓
EmptyResultDataAccessException
↓
nothing maps it to HTTP
↓
Spring Boot default → 500
```

Exception **is** thrown. It is in the **server log**. The client only sees a generic Internal Server Error unless you handle it.

---

## `@ExceptionHandler`

**`@ExceptionHandler`** = a method Spring calls when a given exception type escapes from a request.

First used **on the controller**. Scope = **that controller only**.

```text
exception leaves getEmployee
↓
@ExceptionHandler on EmployeeController
↓
you choose status + body
```

**`@ResponseStatus`** = set HTTP status on a method.  
**`ResponseEntity`** = you control status + body in one object. Use one approach; both together is redundant.

---

## `@RestControllerAdvice`

Controller-level handlers do not cover other controllers. Copying the same handler everywhere is a design smell.

**`@ControllerAdvice`** = a bean that can hold `@ExceptionHandler` methods for **many** controllers.

**`@RestControllerAdvice`** = `@ControllerAdvice` + response-body style (like `@RestController`). Best for REST APIs.

```text
Any controller
↓
exception escapes
↓
GlobalExceptionHandler
↓
HTTP status + ErrorResponse
```

One place. Change once. Applies app-wide (for controllers Spring wires to that advice).

---

## Error body

**`ErrorResponse`** = a small object for the client, e.g. `status` + `message`.

Same shape for every handled error. Do **not** put the Java exception class name in the body.

Prefer package `dto` / `exception`, not `dao`. It is not a DAO class.

---

## Two mappings we practiced

```text
EmptyResultDataAccessException
→ valid id, no row in DB
→ 404 Not Found

MethodArgumentTypeMismatchException
→ path value cannot become int (e.g. /employees/abc)
→ fails while binding, before service/DAO
→ 400 Bad Request
```

For type mismatch, Spring’s **default status was already 400**. The custom handler’s main win is the **same ErrorResponse body**, not inventing 400 from scratch.

For empty row, Spring’s default was **500**. The handler’s win is both **correct status (404)** and **consistent body**.

---

## Final mental model

```text
HTTP request
↓
Controller (no try/catch needed)
↓
Service → DAO
↓
Java exception
↓
@RestControllerAdvice + @ExceptionHandler
↓
ResponseEntity<ErrorResponse>
↓
right status + simple JSON
```

---

# Day 12 — Bean Validation ✅

## Day 12 Objective

Connect:

```text
Day 10 — REST + @RequestBody
        +
Day 11 — GlobalExceptionHandler
        ↓
Day 12 — Bean Validation
```

Today `POST /employees` accepts any JSON shape that Jackson can map. Empty name, blank email, missing salary — can still reach the DAO.

Core question:

> **How does Spring reject bad input before the service runs, and how does that become HTTP 400 with our ErrorResponse?**

Mental model:

```text
JSON body
↓
@RequestBody + @Valid
↓
Bean Validation rules (@NotBlank, @Email, ...)
↓
OK → Controller → Service → DAO
FAIL → MethodArgumentNotValidException
↓
GlobalExceptionHandler
↓
400 + ErrorResponse
```

Jira ticket created.

---

# Day 12 Experiment 1 — Baseline ✅

POST with empty/invalid fields → **200** and row inserted. Wrong.

Client sent bad data. That should fail at the **web edge** before service/DAO. Not 200. Not a silent bad insert.

---

# Day 12 Experiment 2 — Add validation starter ✅

Added `spring-boot-starter-validation`.

Observed: dependency alone does **not** reject bad POST.

Reason:

```text
Starter
↓
validation tools available
↓
rules still not defined
↓
validation still not triggered
```

Field annotations define **rules**. `@Valid` is the **trigger** for this request parameter.

---

# Day 12 Experiment 3 — First rule + `@Valid` ✅

Added one rule (`@NotBlank` on `name`) and `@Valid` on the POST body parameter.

Observed: bad input now fails with HTTP **400** before reaching service/DAO.

The exception appears in the server log. Validation is active.

```text
JSON
↓
Jackson builds Employee
↓
@Valid triggers Bean Validation
↓
@NotBlank on name fails
↓
MethodArgumentNotValidException
↓
400 Bad Request
```

---

# Day 12 Experiment 4 — Custom body for validation failure ✅

Handled `MethodArgumentNotValidException` in `GlobalExceptionHandler`.

Observed: validation failure now returns HTTP **400** with our own `ErrorResponse`.

---

# Day 12 Experiment 5 — More field rules ✅

Added more rules:

- `email` → `@Email`
- `department` → `@NotBlank`
- `salary` → `@NotNull`

Observed: bad input still returns the same generic 400 message. Validation works, but the client still does not know **which field** failed.

---

# Day 12 Experiment 6 — Field-wise validation message ✅

Read the first field error from `MethodArgumentNotValidException`.

Observed:

```json
{
  "status": 400,
  "message": "name: must not be blank"
}
```

This is more useful than a generic “Invalid employee data”.

---

# Day 12 Experiment 7 — First error vs all errors ✅

Chose **all field errors**.

`ErrorResponse.errors` = `Map<String, List<String>>`

- key = field name
- value = list of messages for that field

Observed one request can return multiple fields at once:

```json
{
  "status": 400,
  "message": "Invalide Request Data",
  "errors": {
    "name": ["must not be blank"],
    "email": ["must be a well-formed email address"],
    "salary": ["must not be null"],
    "department": ["must not be blank"]
  }
}
```

A `List` per field supports future cases where one field has several rules failing.

---

# Day 12 CURRENT POSITION — CORE DONE ✅

- Baseline: bad POST still inserts (200) ✅
- `spring-boot-starter-validation` alone does nothing ✅
- Rules ≠ trigger (`@NotBlank` needs `@Valid`) ✅
- `@Valid` + field rules → `MethodArgumentNotValidException` → 400 ✅
- Custom `ErrorResponse` for validation ✅
- All field errors as `Map<String, List<String>>` ✅

---

# Day 12 Experiment 8 — Handy annotations + multi-rule field ✅

Multiple rules on one field can fail together. `errors.name` is a **list**.

Observed for `name: ""`:

```json
"name": [
  "must not be blank",
  "must be greater than or equal to 5"
]
```

That proves `Map<String, List<String>>`.

Important correction: `@Min(5)` on a **String** is not “min 5 characters.” `@Min` / `@Max` are for **numbers**. For string length use `@Size(min = 5, max = 50)`.

---

# Day 12 CURRENT POSITION — READY TO CLOSE ✅

Core + multi-field errors + multi-rule field proven.
Handy annotation set covered.
`@Min` on string length corrected to `@Size`. `@Pattern` used for character rules.

Day 12 = **DONE**

---

# Day 12 — God-Level Notes (Notebook)

## Why

`@RequestBody` only builds the object from JSON. It does not judge if the data is valid.

Without validation, bad POST can reach the service/DAO and even insert with **200**. Wrong.

Validate at the **web edge**, before service/DAO. Bad input → **400**.

```text
Day 10  @RequestBody
Day 11  GlobalExceptionHandler
Day 12  @Valid + Bean Validation → 400 + field errors
```

---

## Starter

**`spring-boot-starter-validation`** is a pack, not one magic class:

- Jakarta Bean Validation API (annotations + contracts)
- Hibernate Validator (runs the rules)
- Spring integration (`@Valid` in MVC, exception on fail)
- Boot auto-config

Adding the starter alone does **not** validate requests. Tools become available. You still need **rules** + **trigger**.

---

## Rules vs trigger

```text
field annotations   = rules   (@NotBlank, @Email, @Size, ...)
@Valid              = trigger (validate this request parameter now)
starter             = support / wiring
```

Rules without `@Valid` → not run for that controller parameter.  
`@Valid` without rules → almost nothing useful to check.

```text
JSON
↓
Jackson builds object
↓
@Valid runs Bean Validation
↓
OK  → controller method runs → service → DAO
FAIL → MethodArgumentNotValidException
     → GlobalExceptionHandler
     → 400 + ErrorResponse
```

---

## Handy annotations

**Presence**

- `@NotNull` — value must exist. `""` and `0` can still pass.
- `@NotEmpty` — not null and not empty. Spaces `"   "` can pass for String.
- `@NotBlank` — String only: not null, not `""`, not only spaces.

**Size / numbers**

- `@Size(min, max)` — string length or collection size.
- `@Min` / `@Max` — **numbers** only (≥ / ≤). Not for string length.
- `@Positive` / `@PositiveOrZero` — number > 0 / ≥ 0.

**Format**

- `@Email` — email-shaped string.
- `@Pattern(regexp = "...")` — must match regex.

**Other**

- `@Past` / `@Future` (and OrPresent variants) — dates/times.
- `@Digits` — digit shape of a number.
- `@AssertTrue` / `@AssertFalse` — boolean must be true/false.

Multiple annotations on one field are **AND**. All must pass. Several can fail together.

---

## Error response shape

Prefer all field errors, not only the first.

```text
ErrorResponse
- status
- message
- errors → Map<String, List<String>>
```

- key = field name
- value = list of messages for that field

One field can have several failed rules → a list, not a single string.

`MethodArgumentNotValidException` → `getBindingResult().getFieldErrors()` → group by field.

---

## Mental model

```text
POST JSON
↓
@RequestBody + @Valid
↓
field rules
↓
fail → MethodArgumentNotValidException
↓
@RestControllerAdvice
↓
400 + { status, message, errors }
```

Do not put Bean Validation as the first defense inside the DAO. Web edge rejects bad input. Service/DAO deal with business and SQL.

---

# Day 13 — Request / Response DTOs ✅

## Day 13 Objective

Connect:

```text
Day 10 — REST (@RequestBody Employee)
        +
Day 12 — Validation on Employee
        ↓
Day 13 — Separate API DTOs from the domain/DB model
```

Today the controller accepts and returns `Employee` directly. Validation annotations sit on the same class used for DB mapping. That mixes **API contract** and **persistence model**.

Core question:

> **Why should the HTTP layer not use the database Employee model as the request/response body forever?**

Mental model:

```text
JSON
↓
Request DTO (+ @Valid)
↓
Controller
↓
map to Employee (domain)
↓
Service → DAO → DB
↓
map to Response DTO
↓
JSON
```

Jira ticket created.

---

# Day 13 Experiment 1 — Baseline ✅

Understood:

- **DTO** = object whose job is to **carry data** across a boundary (here: HTTP ↔ app). Not business logic. Not SQL.
- Problem with one `Employee` for HTTP + DB: API may expose or accept fields it should not (e.g. secrets, internal fields, or `id` on create).
- Validation for the HTTP body belongs on the **request DTO**, not on the persistence `Employee`.

---

# Day 13 Experiment 2 — Create request DTO ✅

Created `CreateEmployeeRequest` with validation. `Employee` is clean again (no validation annotations).

POST flow:

```text
JSON → CreateEmployeeRequest (@Valid)
↓
controller maps to Employee
↓
EmployeeService.create(Employee)
↓
still returns Employee (next: response DTO)
```

Service stays on `Employee`. Jackson builds the request DTO via no-arg + setters. JSON number → `BigDecimal` works.

---

# Day 13 Experiment 3 — Response DTO ✅

Created `EmployeeResponse`. GET one, GET list, and POST return response DTOs.

```text
Service returns Employee
↓
controller maps to EmployeeResponse
↓
Jackson serializes response DTO to JSON
```

For serialization (Java → JSON), getters **or** public fields are enough. Setters are for deserialization (JSON → Java).

---

# Day 13 Experiment 4 — Update request DTO (PATCH) ✅

`UpdateEmployeeNameRequest` with validation. PATCH no longer takes `Employee`.

```text
PATCH → UpdateEmployeeNameRequest (@Valid)
↓
service.updateEmployeeName(id, name)
```

Different API actions can have different request DTOs (create vs update name).

---

# Day 13 CURRENT POSITION — CORE DONE ✅

- DTO = carry data across HTTP boundary ✅
- Request DTO + validation; Employee stays domain/DB ✅
- Service still takes Employee (not web DTO) ✅
- Response DTO for GET/POST ✅
- Update request DTO for PATCH ✅

---

# Day 13 Experiment 5 — Standard success responses (`ResponseEntity`) ✅

REST success style (no success envelope):

```text
POST   → 201 CREATED + EmployeeResponse
PATCH  → 200 OK (no body)   // 204 also fine when body is empty
DELETE → 204 NO_CONTENT
GET    → 200 + EmployeeResponse / list
errors → ErrorResponse
```

`ResponseEntity` = status + optional body. Client reads success from the **status code**, not from a `boolean`.

---

# Day 13 CURRENT POSITION — READY TO CLOSE ✅

DTOs + standard HTTP success statuses done.

Day 13 = **DONE**

---

# Day 13 — God-Level Notes (Notebook)

## Why DTO

**DTO (Data Transfer Object)** = an object whose job is to **carry data across a boundary**.

Here the boundary is HTTP ↔ application.

Do not use the DB/domain `Employee` as the forever request/response body.

Problems with one class for HTTP + DB:

- may expose or accept fields the API should not (secrets, internal fields, `id` on create)
- API shape and table shape should be able to change separately
- validation for HTTP belongs on the request contract, not on the persistence model

```text
JSON
↓
Request DTO (+ @Valid)
↓
map
↓
Employee (domain)
↓
Service → DAO → DB
↓
map
↓
Response DTO
↓
JSON
```

---

## Request vs response vs domain

| Type | Job |
|------|-----|
| `CreateEmployeeRequest` | POST body + validation. No `id`. |
| `UpdateEmployeeNameRequest` | PATCH body (only what this API changes) + validation |
| `EmployeeResponse` | what the API returns |
| `Employee` | domain / DB model. Service and DAO use this. |

Validation annotations live on **request DTOs**, not on `Employee`.

Service methods stay on `Employee` (or primitives like `id` + `name`). Do **not** push web DTOs into the service layer — that couples business code to the HTTP shape.

Controller (or a small mapper) does the mapping.

---

## Jackson and DTOs

**Deserialize** (JSON → Java), e.g. request DTO:

```text
no-arg constructor
↓
setters for JSON properties present
```

Private fields + getters + setters is the usual style.  
Public fields also work.  
JSON number can bind to `BigDecimal`.

Jackson does **not** call every setter. Only setters for properties present in the JSON.

**Serialize** (Java → JSON), e.g. response DTO:

Needs a way to **read** values: getters **or** public fields.  
Setters are not required for output-only DTOs.

---

## Success responses (standard REST)

Do **not** wrap every success in `{ status, message, data }` unless a client standard forces it.

Prefer:

| Action | Status | Body |
|--------|--------|------|
| GET one / list | 200 | `EmployeeResponse` / list |
| POST create | **201** Created | `EmployeeResponse` |
| PATCH update | **200** (+ body) or **204** (no body) | not 201 |
| DELETE | **204** No Content | empty — no `boolean` |
| Errors | 4xx/5xx | `ErrorResponse` |

**`ResponseEntity`** = you control HTTP status + optional body.

```text
201 = new resource created
200 = OK (read/update)
204 = success, no body
```

Client learns delete/update success from the **status code**, not from `true`/`false` in JSON.

Errors stay consistent via `GlobalExceptionHandler` + `ErrorResponse`. Success body is the **resource** (or empty).

---

## Mental model

```text
Web edge:  Request DTO / Response DTO / ErrorResponse / ResponseEntity
Inside:    Employee + Service + DAO
```

---

# Day 14 — Spring Data JPA Intro ✅

## Day 14 Objective

Connect:

```text
Days 1–3  JDBC / JdbcTemplate / DAO
Days 10–13 REST API on EmployeeDAO
        ↓
Day 14 — Spring Data JPA (new persistence style)
```

Teach from scratch. Keep JDBC code. Do not dump full JPA theory at once.

Core question:

> **Why map tables to Java objects, and how does Spring Data JPA give CRUD without writing every SQL string yourself?**

Mental model being built:

```text
Controller / Service
↓
JpaRepository (Spring Data)
↓
JPA API
↓
Hibernate (engine)
↓
DataSource / HikariCP
↓
MySQL
```

Jira ticket created.

---

# Day 14 Experiment 1 — ORM WHY (baseline) ✅

With JDBC/JdbcTemplate DAO:

- You write the SQL.
- You map `ResultSet` → `Employee`.

ORM goal: for common CRUD, the framework generates SQL and maps rows ↔ objects.

You still own: the **mapping rules** (which class/fields ↔ which table/columns), business logic, and special queries when defaults are not enough. You do not only “pass an object” with zero design.

---

# Day 14 Experiment 2 — JPA vs Hibernate vs Spring Data ✅

Simple roles (from scratch):

- **JPA** = specification / API (rules and annotations for persistence). Not a DB driver.
- **Hibernate** = the common **implementation** that runs those rules (Boot’s default).
- **Spring Data JPA** = Spring convenience on top: repositories (`save`, `findById`, …) so you rarely touch `EntityManager` at the start.

`EntityManager` = JPA’s lower-level “talk to persistence” API. We skip it for now and use Spring Data repositories first.

---

# Day 14 Experiment 3 — Add `spring-boot-starter-data-jpa` ✅

App still starts. REST JDBC path still works.

Observed in logs:

```text
Bootstrapping Spring Data JPA repositories
Found 0 JPA repository interfaces
Hibernate Processing PersistenceUnitInfo
Initialized JPA EntityManagerFactory
```

Meaning: JPA/Hibernate is wired. No entity and no repository yet → **0 repositories**.

(Optional later: `spring.jpa.open-in-view` warning. Park for now.)

---

# Day 14 Experiment 4 — First entity ✅

Created `EmployeeEntity` mapped to `employees`.

```text
@Entity + @Table + @Id + IDENTITY
```

App still starts. Hibernate initializes with the entity present.

Duplicate “Database driver / catalog” lines in the console are a Hibernate startup logging quirk, not a failed mapping.

Still `Found 0 JPA repository interfaces` — expected until we create a repository.

Controller still uses DTOs only. Correct.

---

# Day 14 Experiment 5 — First Spring Data repository ✅

```java
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer> {}
```

Log: `Found 1 JPA repository interface.` App still works.

Spring created a repository bean from the interface. No SQL written by you yet.

---

# Day 14 Experiment 6 — Call repository from service ✅

`findAll()` and `findById()` used via `EmployeeRepository`.
Entity → domain `Employee` mapping in the service. Controller/DTOs unchanged.

`findById` returns `Optional<EmployeeEntity>`. Current code uses `.get()`.

Important: missing id no longer throws `EmptyResultDataAccessException` (that was JdbcTemplate). Empty `Optional.get()` → `NoSuchElementException` → likely **500**, not the Day 11 404 handler.

---

# Day 14 Experiment 7 — `Optional` and not-found ✅

`findById` → check empty → throw `NoSuchElementException` with message.
`GlobalExceptionHandler` maps it to **404** + `ErrorResponse`.

Same idea as `orElseThrow`. Clearer than bare `.get()` with no check.

JDBC empty result was `EmptyResultDataAccessException`. JPA not-found via `Optional` is a different exception path.

---

# Day 14 Experiment 8 — `save()` for create ✅

`create` uses `employeeRepository.save(entity)`. No id set before save.

After INSERT, the generated id is available on the returned/managed entity (DB generates it; Hibernate sets it on the entity — usually from the insert’s generated key, not a separate full fetch).

---

# Day 14 Experiment 9 — Delete + update via repository ✅

Update: find → setName → save (partial update without wiping other columns).

Delete: `deleteById`. Prefer also checking exists / find first if you want a clear **404** when id is missing (behavior of delete-on-missing can vary by Spring Data version).

---

# Day 14 CURRENT POSITION — INTRO CRUD DONE ✅

From scratch covered:

- ORM WHY ✅
- JPA vs Hibernate vs Spring Data ✅
- starter + entity + repository ✅
- findAll / findById / save / update / delete ✅
- Optional → 404 ✅
- DTO at controller; entity at persistence ✅

Ready for day review → God-level notes → Jira Done.

(Later days: relationships, derived query methods, EntityManager, open-in-view, etc.)

Day 14 intro = **DONE**

---

# Day 15 Experiment 6 — Bridge to Spring Data JPA ✅

`repository.save()` / `findById()` / `deleteById()` are convenience wrappers over `EntityManager` (see Day 14 notes rewrite).

---

# Day 15 — God-Level Notes (Notebook)

## Stack (bottom → top)

```text
EntityManager API        ← what you call in JPA code
Persistence context      ← tracker (managed objects, dirty checking, identity map)
Hibernate                ← JPA implementation (generates SQL)
JDBC / DataSource        ← MySQL
```

**ORM** = work with objects; framework generates SQL + maps rows ↔ objects + tracks changes.

You still own: mapping rules, business logic, special queries.

---

## Persistence context

**Persistence context (PC)** = JPA's working memory inside a transaction.

Jobs:

- track which Java objects = which DB rows
- watch entity state (new / changed / deleted)
- flush SQL at the right time (flush / commit)
- identity map: same id in same PC → often **same Java instance**

PC exists inside a **transaction boundary**. No stable PC for writes without `@Transactional`.

Changing a plain Java object (JDBC style) does **not** hit the DB until you write SQL yourself.

---

## EntityManager vs EntityManagerFactory

| | Role | Analogy |
|---|------|---------|
| **EntityManagerFactory (EMF)** | expensive; one per app; reads config + entities | `DataSource` |
| **EntityManager (EM)** | unit of work; per transaction | `Connection` |

Boot creates EMF at startup (`Initialized JPA EntityManagerFactory`).

Inject `EntityManager` via constructor (preferred) or `@PersistenceContext`. Spring gives a **transaction-scoped proxy**.

`@Transactional` opens the transaction + binds PC. Injection gives you the **handle** to call `persist` / `find`. You need **both**.

---

## Four entity states

| State | Meaning | Example |
|-------|---------|---------|
| **transient** | new object; JPA does not know it; no row | `new EmployeeEntity()` |
| **managed** | inside PC; tied to a row; changes can sync | `em.find()` inside `@Transactional` |
| **detached** | was managed; tx ended; PC closed | hold reference after service method returns |
| **removed** | marked for DELETE on flush/commit | `em.remove(managed)` |

Interview lines:

- **managed** + `setName()` → UPDATE at flush/commit (dirty checking). No explicit `save`.
- **detached** + `setName()` → Java only. DB unchanged until `merge` in a new tx.

---

## EntityManager methods

| Method | For | Effect |
|--------|-----|--------|
| `persist(e)` | **transient** only | → managed → INSERT on flush |
| `find(Class, id)` | load by PK | → managed (or null) |
| `merge(e)` | **detached** (or untracked) | copy into PC → managed → UPDATE (or INSERT) |
| `remove(e)` | **managed** | → removed → DELETE on flush |
| `flush()` | force SQL now | still inside same transaction |

Rules:

- `persist(detached)` → wrong / error
- `merge()` returns **managed copy** — use return value for further changes
- `merge(transient)` can work; `persist` is correct for new entities

---

## Dirty checking

Hibernate compares managed entity vs snapshot taken when it entered the PC.

Change a field on a **managed** entity → marked dirty → SQL at **flush** time (explicit `flush()` or automatic flush before commit).

Not instant on every setter. No SQL until flush/commit.

`repository.save(managedEntity)` in Spring Data triggers the same dirty-check + flush path.

---

## IDENTITY id generation

`@GeneratedValue(strategy = GenerationType.IDENTITY)` → DB generates id (`AUTO_INCREMENT`).

Hibernate often runs **INSERT immediately on `persist()`** to read generated key → id available **before** explicit `flush()`.

Do not assume all strategies behave this way (SEQUENCE/TABLE can differ).

---

## `@Transactional` — Spring vs Jakarta

| | Use in Spring Boot + JPA? |
|---|---------------------------|
| `org.springframework.transaction.annotation.Transactional` | **Yes — always** |
| `jakarta.transaction.Transactional` | Avoid — JTA-style; weaker JPA flush-on-commit integration |

Observed bug: Jakarta annotation → INSERT worked (IDENTITY early flush) but commit-time UPDATE from dirty checking did not. Spring's annotation fixed it.

`@Transactional` on **service** layer. Not controller.

---

## CommandLineRunner (learning tool)

`CommandLineRunner.run(args)` called **once after** full startup (context + Tomcat ready).

Use for: seed data, one-time scripts, **JPA learning demos**.

Not for: REST APIs or per-request work.

Full DI works — call `@Transactional` service methods from runner.

`@Profile("learning")` optional — avoid delete/seed on every prod boot.

`ApplicationRunner` = same timing, parsed CLI args (`--id=5`).

---

## Mental model

```text
@Service @Transactional
↓
EntityManager (proxy)
↓
Persistence context (per transaction)
↓
Hibernate → SQL
↓
MySQL
```

---

# Day 14 — God-Level Notes (Notebook) — rewritten after Day 15

## Why ORM

With JDBC / `JdbcTemplate` DAO:

- **you** write SQL
- **you** map `ResultSet` → Java object
- **you** track what changed and when to UPDATE

**ORM** = map Java objects ↔ database rows. Framework generates common SQL, maps rows ↔ objects, tracks changes via persistence context.

You still own: mapping rules, business logic, special queries.

---

## Three names (same stack, different layer)

```text
Spring Data JPA  → convenience (repository methods)
        ↓
EntityManager    → JPA API (persist, find, merge, remove)
        ↓
Persistence context → tracker behind EntityManager
        ↓
Hibernate        → implementation / engine (default in Boot)
        ↓
JDBC / DataSource / Hikari → MySQL
```

- **JPA** = specification / API. Not a DB driver.
- **Hibernate** = engine that implements JPA.
- **Spring Data JPA** = generates repository proxy so you rarely write `EntityManager` calls for basic CRUD.

Day 15 = what happens **under** the repository.

---

## Entity

**Entity** = Java class mapped to a table. Object ≈ row. Field ≈ column.

```text
@Entity
@Table(name = "employees")
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
```

Needs **no-arg constructor** (Java default is fine if no other constructor).

Entity at **persistence layer only**. Do not use as `@RequestBody` — keep DTOs at controller (Day 13).

---

## Repository

```java
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer> {}
```

- First type = entity class
- Second type = id type (`Integer` for `int` id)

Spring Data scans `JpaRepository` interfaces → creates **proxy bean**. No `@Component` on interface.

Inherited: `save`, `findById`, `findAll`, `deleteById`, …

---

## Repository method → EntityManager (bridge)

| Repository | Under the hood (conceptually) |
|------------|-------------------------------|
| `save(newEntity)` (no id) | `persist` → INSERT |
| `save(detachedEntity)` (has id, tx ended) | `merge` → UPDATE |
| `save(managedEntity)` (changed in same tx) | dirty checking → UPDATE on flush |
| `findById(id)` | `em.find` → managed (inside tx) or detached (after tx) |
| `findAll()` | SELECT all |
| `deleteById(id)` | load + `remove` or direct DELETE (implementation detail) |

`save()` is **not** always INSERT. Spring Data checks entity state: new → persist, existing/detached → merge.

That is why Day 15 matters before trusting `save()` blindly.

---

## CRUD we practiced

| Action | Call | Note |
|--------|------|------|
| List | `findAll()` | |
| Get one | `findById(id)` → `Optional` | empty ≠ JDBC `EmptyResultDataAccessException` |
| Create | `save(newEntity)` without id | INSERT; id from DB (`IDENTITY`) |
| Update field | `findById` → set field → `save` | load first; partial update safe. Same as managed dirty check. |
| Delete | `deleteById(id)` | check exists first if you need clear **404** |

`findById` empty → throw `NoSuchElementException` → `GlobalExceptionHandler` → **404**.

Partial update trap: new entity with only id+name + `save` can null out other columns. **Always load first.**

`spring.jpa.show-sql=true` → Hibernate SQL in logs.

---

## Mental model

```text
Controller (DTO)
↓
Service (@Transactional, domain Employee, map ↔ entity)
↓
EmployeeRepository (Spring Data proxy)
↓
EntityManager + persistence context
↓
Hibernate → SQL
↓
MySQL employees
```

JDBC DAO can stay in project for comparison. Main API path uses JPA.

---

## Starter + startup signals

`spring-boot-starter-data-jpa` wires JPA + Hibernate + Spring Data.

Logs to recognize:

```text
Bootstrapping Spring Data JPA repositories
Found N JPA repository interfaces
Initialized JPA EntityManagerFactory
```

`spring.jpa.open-in-view=true` (Boot default) — keeps session open through web render for lazy loading. Park for later; can cause extra queries.

---

# Day 15 — CommandLineRunner (learned alongside Exp 5)

## Day 15 Objective

```text
ORM idea
↓
JPA concepts (EntityManager, persistence context)
↓
Entity lifecycle (transient / managed / detached / removed)
↓
Basic operations: persist, find, merge, remove
↓
Transactions with JPA
↓
Bridge: how Spring Data JPA sits on top (see Day 14 notes rewrite)
```

Core question answered:

> **What does JPA actually do underneath a repository, and what are entity states?**

Jira ticket created. All experiments done.

---

# Day 15 Experiment 1 — WHY persistence context? ✅

Answers (refined):

1. **No.** After `setName(...)` on a JDBC-loaded object, the DB is unchanged. You must run `UPDATE` (or similar) yourself. Changing the Java object does not touch the database.
2. **One SELECT is enough** in the same transaction. The persistence context can return the **same managed instance** for the same id (identity map). Avoids duplicate queries and keeps one in-memory truth for that row.
3. **Persistence context should:** track which Java objects represent DB rows, watch their state (new/changed/deleted), and flush SQL at the right time (usually commit/flush). **Should not:** replace `@Transactional`, guess business rules, or keep objects "live" forever across transactions without `merge`.
4. **ORM saves you from:** hand-written repetitive SQL for map row↔object, INSERT/UPDATE column lists, and manual change tracking — not just "mapping" in the abstract.

---

# Day 15 Experiment 2 — Entity states (mental model) ✅

- A → **transient** (plain `new`, JPA does not track it)
- B → **managed** (`em.find` inside active persistence context / transaction)
- C → **managed** (dirty checking — no explicit `save` needed; change syncs at flush/commit)
- D → **detached** (transaction ended; context closed; object still in memory but untracked)
- E → **removed** (scheduled for DELETE on flush/commit)

Key interview line: **managed + change field = JPA can auto-UPDATE at flush. Detached + change field = DB unchanged until `merge`.**

---

# Day 15 — EntityManager mental model (concept) ✅

- **EntityManager** = API you call (`persist`, `find`, `merge`, `remove`)
- **Persistence context** = tracker behind it (managed objects, dirty checking, identity map)
- **EMF** = factory (expensive, one per app); **EM** = unit of work (per transaction)
- PC is scoped to transaction / `EntityManager` session → writes need `@Transactional`
- **`persist`** → transient only (new row, INSERT)
- **`merge`** → detached (or untracked) entity → copy into PC → UPDATE (or INSERT if new)
- `persist(detached)` → wrong / error. `merge(transient)` → can work but `persist` is the right tool for new entities
- Tx active + `find` → **managed**; tx ended → **detached**; `setName` on detached → **no DB hit** until `merge` in new tx

---

# Day 15 Experiment 3 — First EntityManager (persist + find) ✅

`JPAEmployeeService.persistAndFind`:
- transient id=0 → `persist` → INSERT runs → id=12 **before** explicit `flush` (IDENTITY strategy)
- `entity == found` → **true** (same PC / identity map, no second SELECT)
- `flush()` after INSERT changed nothing (already flushed by `persist` + IDENTITY)

---

# Day 15 Experiment 4 — Dirty checking (managed, no save) ✅

- Managed entity + `setDepartment("HR")` inside tx → should UPDATE at flush/commit (no explicit save)
- Failed with `jakarta.transaction.Transactional` — INSERT ran (IDENTITY early flush) but commit-time UPDATE did not
- Fixed with `org.springframework.transaction.annotation.Transactional` — dirty changes flushed on commit
- Rule: Spring Boot + JPA → always use **Spring's** `@Transactional`, not Jakarta's

---

# Day 15 Experiment 5 — remove + merge ✅

- `removeDemo`: `find` → `remove` → DELETE on commit
- `mergeDemo`: detached + `setName` → no SQL; `merge` + commit → UPDATE
- Demos wired via `CommandLineRunner` (`JpaLearningRunner`) instead of HTTP routes

**merge lock-in:** use **return value** of `merge()` for further changes — not the old detached reference.

---

# Day 15 Experiment 6 — Bridge to Spring Data JPA ✅

- Interface: `run(String... args)` called **once after** full ApplicationContext + Tomcat startup
- Use: startup scripts, seed data, **learning demos** — not for request handling
- Full DI works; call `@Transactional` service methods from runner (proxy still applies)
- `@Profile("learning")` optional — avoids delete/seed logic on every prod boot
- Alternative: `ApplicationRunner` when you need parsed CLI flags (`--id=5`)

---

# Day 16 — JPA Relationships ✅ DONE

## Day 16 Objective

Connect:

```text
Day 15 — Entity, EntityManager, entity states, persistence context
        ↓
Day 16 — JPA relationships (@ManyToOne, @OneToMany, lazy/eager)
```

Core question:

> **How do you model real-world links between tables (employee ↔ department) in JPA, and what happens when you load one side?**

In scope:

- WHY relationships (stop duplicating department string on every employee row)
- `@ManyToOne` / `@OneToMany` basics
- Owning side vs inverse side
- `mappedBy`
- `FetchType.LAZY` vs `EAGER` (mental model + `LazyInitializationException` preview)
- Practical experiment: `Department` entity + link to `EmployeeEntity`
- Cascade basics (what to park vs cover lightly)

Out of scope (later days):

- `@ManyToMany`
- Full `open-in-view` day (Day 19 in sequence — option 4)
- Bidirectional sync deep dive / orphanRemoval advanced cases

Jira ticket created.

---

# Day 16 Experiment 1 — WHY relationships? ✅

Answers (refined):

1. String column **works for tiny demos** but breaks at scale: duplicate data, typos (`IT` vs `it`), rename = update many rows, no single place for department metadata (location, manager).
2. **Foreign key** = DB enforces a link to one real `departments` row. One source of truth. Invalid department id rejected. Rename department = update **one** row.
3. **Many employees → one department.** Employee = many side. Department = one side.
4. **`@ManyToOne` on Employee is enough** to get `employee.getDepartment().getName()`. `@OneToMany` on Department only needed if you also want `department.getEmployees()` from the other side.

---

# Day 16 Experiment 2 — Department entity + @ManyToOne ✅

- `DepartmentEntity` + `employees.department_id` FK
- `EmployeeEntity`: `@ManyToOne` + `@JoinColumn(name = "department_id")`
- `getEmployee(9)` → printed department name (`IT`)
- SQL: **one** query with `LEFT JOIN departments` (default `@ManyToOne` = **EAGER**)

---

# Day 16 Experiment 3 — LAZY vs EAGER ✅

Run A (LAZY, access outside tx): employee SELECT only → `LazyInitializationException`
Run B (LAZY, access inside `@Transactional`): 2 SELECTs (employee, then department) → OK
Run C (EAGER): 1 SELECT with LEFT JOIN → OK, no second query

Rule: prefer `@ManyToOne(fetch = LAZY)`; load association inside tx or map before return.

---

# Day 16 Experiment 4 — @OneToMany + mappedBy ✅

- `@OneToMany(mappedBy = "department")` on Department = **inverse** side (no FK column)
- `@ManyToOne` + `@JoinColumn` on Employee = **owning** side (`department_id`)
- LAZY (default `@OneToMany`): dept SELECT → `getEmployees().size()` outside tx → `LazyInitializationException`
- EAGER `@OneToMany`: one JOIN → count works outside tx but over-fetches
- Production: keep both sides LAZY; load collections inside `@Transactional` service

---

# Day 16 — God-Level Notes (Notebook)

## What is a JPA relationship?

JPA maps **links between Java objects** to **links between database tables**.

```text
Java objects  ↔  JPA  ↔  DB tables
```

Example: many employees belong to one department.

```text
Employee N ───── 1 Department
```

---

## WHY not a string column?

Today we had `employees.department = "IT"` (string).

Works for tiny demos. Problems at scale:

| Problem | Example |
|---------|---------|
| Duplication | 100 rows all store `"IT"` |
| Rename pain | change name → UPDATE 100 rows |
| Typos | `"IT"`, `"it"`, `"I.T."` treated as different |
| No metadata | `location`, `manager` on department → copy on every employee row |

**Fix:** separate `departments` table + **foreign key** `employees.department_id → departments.id`.

One department row = single source of truth.

---

## Cardinality (how many?)

| Relationship | Meaning |
|--------------|---------|
| 1 : 1 | one ↔ one |
| 1 : N | one → many |
| N : 1 | many → one |
| N : N | many ↔ many |

Same link, two views:

```text
Department 1 ───── N Employee

Department → Employee  = 1:N   (@OneToMany)
Employee → Department  = N:1   (@ManyToOne)
```

Day 16 practiced **N:1 / 1:N** only. `@OneToOne` / `@ManyToMany` parked for later.

---

## Four JPA relationship annotations

```text
@OneToOne
@OneToMany
@ManyToOne
@ManyToMany
```

Read from **this entity's perspective**:

```java
@ManyToOne
DepartmentEntity department;   // many employees → one department
```

---

## Foreign key (DB view)

```text
departments          employees
| id | name |        | id | name | department_id |
| 1  | IT   |   ←──  | 1  | Vij  | 1             |
```

`department_id` = FK column. Points to `departments.id`.

JPA `@ManyToOne` + `@JoinColumn` maps this column.

---

## @ManyToOne + @JoinColumn (what we built)

```java
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "department_id")
private DepartmentEntity department;
```

| Piece | Meaning |
|-------|---------|
| `@ManyToOne` | many employees → one department |
| `@JoinColumn(name = "department_id")` | **DB column name** for FK |
| Java field `department` | object navigation |

**Default fetch for `@ManyToOne` = EAGER** (JPA spec). We set **LAZY** in production style.

---

## Unidirectional vs bidirectional

**Unidirectional** — navigate one way only:

```java
// Employee has department. Department has NO employees list.
employee.getDepartment();   // ✅
department.getEmployees();  // not modeled
```

**Bidirectional** — navigate both ways:

```java
employee.getDepartment();   // ✅
department.getEmployees();  // ✅
```

**Bidirectional ≠ two DB relationships.** One FK in DB. Two Java navigation paths.

Day 16: started unidirectional (`@ManyToOne` only), then added `@OneToMany` for reverse navigation.

---

## Owning side vs inverse side

For bidirectional FK mapping:

```java
// OWNING side — controls FK in DB
@ManyToOne
@JoinColumn(name = "department_id")
private DepartmentEntity department;

// INVERSE side — navigation only, no FK column
@OneToMany(mappedBy = "department")
private List<EmployeeEntity> employees;
```

| Side | Role |
|------|------|
| **Owning** (`Employee.department`) | defines FK mapping; `employee.setDepartment(dept)` updates `department_id` |
| **Inverse** (`Department.employees`) | collection view; does not own the column |

Do **not** memorize "@ManyToOne is always owner."  
Rule: **owning side = side with `@JoinColumn` / FK column.**

Only owning side changes the DB link.

---

## mappedBy

```java
@OneToMany(mappedBy = "department")
private List<EmployeeEntity> employees;
```

`mappedBy = "department"` = Java field name on **EmployeeEntity** that owns the link.

```text
mappedBy = "department"     ✅ Java field
mappedBy = "department_id"  ❌ DB column name
```

Without `mappedBy`, JPA may treat `@OneToMany` and `@ManyToOne` as **two separate mappings**.  
`mappedBy` tells JPA: **same relationship, two sides.**

---

## mappedBy vs @JoinColumn

| Annotation | Points to |
|------------|-----------|
| `@JoinColumn(name = "...")` | **DB column** |
| `mappedBy = "..."` | **Java field name** on other entity |

---

## Design framework (interview)

For two linked entities, ask in order:

1. **Cardinality** — 1:1, 1:N, N:1, N:N?
2. **Direction** — one-way navigation or both?
3. **Ownership** — which side has `@JoinColumn` / FK?

Example (our project):

```text
Cardinality:  N employees → 1 department
Direction:    bidirectional (employee → dept, dept → employees)
Ownership:    Employee.department owns department_id
```

---

## FetchType — LAZY vs EAGER

**Problem:** when parent loads, also load association now or later?

| | **EAGER** | **LAZY** |
|---|-----------|----------|
| **When** | with parent | on first access (`getDepartment()` / `getEmployees()`) |
| **SQL** | often one JOIN | separate SELECT when touched |
| **Risk** | over-fetching | `LazyInitializationException` outside tx |

**Defaults (JPA):**

```text
@ManyToOne   → EAGER (override to LAZY in real apps)
@OneToMany   → LAZY
```

### What we observed

**`@ManyToOne` EAGER:** one JOIN query; department available after tx.  
**`@ManyToOne` LAZY + access in runner after tx:** `LazyInitializationException`.  
**`@ManyToOne` LAZY + access inside `@Transactional` service:** 2 SELECTs, OK.

**`@OneToMany` LAZY (default) + `getEmployees().size()` in runner:** exception.  
**`@OneToMany` EAGER:** one JOIN, count works but loads all employees every time.

---

## LazyInitializationException

Happens when you touch a **LAZY** association after persistence context is closed (transaction ended).

```text
@Transactional service → find entity → return entity
tx ends → PC closed
runner/controller → entity.getDepartment()  → CRASH
```

Entity is **detached** (or proxy has no session). Hibernate cannot run lazy SELECT.

---

## Standard fix (production pattern)

Keep associations **LAZY**. Touch them **inside `@Transactional` service**. Return plain data (String, int, DTO) — not raw entity with lazy proxies to controller/runner.

```java
@Transactional
public int getEmployeeCount(int departmentId) {
    DepartmentEntity dept = entityManager.find(DepartmentEntity.class, departmentId);
    return dept.getEmployees().size();   // lazy load inside tx → OK
}
```

```text
Controller / Runner  →  no lazy loading
@Service @Transactional  →  lazy load here
Return DTO / int / String  →  safe outside tx
```

This is the **standard Spring + JPA way** — not a workaround.

Do **not** switch to EAGER just to fix runner/controller exceptions.

---

## Mental model (full stack)

```text
Controller (DTO)
↓
@Service @Transactional
↓
EmployeeEntity  @ManyToOne(LAZY)  →  DepartmentEntity
DepartmentEntity  @OneToMany(mappedBy, LAZY)  →  List<EmployeeEntity>
↓
EntityManager + persistence context (per tx)
↓
employees.department_id  →  departments.id
```

---

## Five rules to remember

1. **Cardinality** = what the relationship is (1:N, N:1, …).
2. **Direction** = which way Java can navigate (uni vs bidirectional).
3. **Ownership** = which side has `@JoinColumn` / FK; only that side updates DB link.
4. **`mappedBy`** = Java field name on owner, not DB column.
5. **Bidirectional** = two Java paths, **one** DB relationship. Prefer **LAZY** + load inside transactional service.

---










































