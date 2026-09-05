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

Agent / Cursor rules:

- When updating this project via Cursor: **only edit this `.md` file** — never Java, config, or other source files unless I explicitly ask otherwise.
- Every completed day must be recorded here in the same format as prior days: objective → experiments (with observations) → God-level notes → hard rules → what's next.
- Do not skip experiment sections or God-level notes when a day is marked done.

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

# Day 17 — Spring Data Query Methods ✅ DONE

## Day 17 Objective

Connect:

```text
Day 14 — JpaRepository basic CRUD (save, findById, findAll)
Day 16 — Employee ↔ Department relationship
        ↓
Day 17 — Derived query methods (findByX, And, Or, Containing, …)
```

Core question:

> **How does Spring Data generate SQL from a repository method name, and how do I query by department, name, salary, etc. without writing SQL?**

In scope:

- WHY derived queries (avoid boilerplate for simple reads)
- Method naming rules (`findBy`, `readBy`, `getBy`, `queryBy`, …)
- Property traversal (`findByDepartment_Name`)
- Keywords: `And`, `Or`, `Containing`, `IgnoreCase`, `OrderBy`, `Between`, `IsNull`
- Return types: `List`, `Optional`, `boolean exists…`
- Observe generated SQL with `show-sql`
- Use in `EmployeeService` / optional temp endpoint

Out of scope (Day 18):

- `@Query` / JPQL
- Native SQL queries
- Specifications / Criteria API

Do not start experiments until Jira ticket exists.

Jira ticket created.

---

# Day 17 Experiment 1 — WHY derived queries? ✅

Answers (refined):

1. **Correct** — loads all rows, filters in JVM → waste memory/CPU/network; slow at scale.
2. **Spring Data** parses method name → builds query. **Hibernate** generates/runs SQL. Both layers; not "you write SQL" and not Hibernate alone from method name.
3. **`Department`** = relationship field on `EmployeeEntity`. **`Name`** = property on `DepartmentEntity`. Underscore `_` = navigate into associated entity (`findByDepartment_Name`).
4. **`findByName`** → queries **`EmployeeEntity.name`** (employee name). No exception — method name maps to entity property path. Only one `name` on Employee at top level.

---

# Day 17 Experiment 2 — First derived methods ✅

Observed:
- `findByDepartment_Name("IT")` → query 1: employees JOIN departments (WHERE d.name=?) ✅ filter in DB
- query 2: `SELECT ... FROM departments WHERE id=?` → **not from method name** — caused by `@ManyToOne(fetch = EAGER)` on `EmployeeEntity.department` hydrating after load
- `findByName("Test")` → 1 query only (no department load needed if no rows / no eager touch). Empty result = exact match failed — check DB for exact `name` value (not `Vijendra Test`, not trailing space)
- `findBySalaryGreaterThan` → query 1: employees WHERE salary>? ; query 2: department by id (EAGER again)
- `findByDep_Name` → `QueryCreationException` — no field `dep` on EmployeeEntity (must match Java property: `department`)
- EAGER extra query ≠ derived query bug — JOIN in derived query = filter; EAGER hydrates association separately
- LAZY + only `getName()` → 1 query; LAZY + `getDepartment()` inside tx → 2+ queries (N+1 risk if many employees, same dept may dedupe to 2)

---

# Day 17 Experiment 3 — More keywords (And, Containing, OrderBy) ✅

- `ContainingIgnoreCase` → `UPPER(name) LIKE` → matched Vijendra Test + Test
- `And` → JOIN + `WHERE dept.name AND salary>` → filtered in DB
- `OrderBySalaryDesc` → `ORDER BY salary DESC` in SQL (Test → Vijendra Test → Shimbhu for IT)
- JPA uses `department_id` / relationship — stale string `department` column can lie (id 3 shows HR string but department_id=1)

---

# Day 17 Experiment 4 — existsBy / Optional return ✅

- `findByEmail` → `Optional<EmployeeEntity>` — present or empty
- `existsByEmail` → `boolean` — efficient existence check (SELECT 1 / count style, not full entity load)

---

# Day 17 — Spring Data Query Methods ✅ DONE

---

# Day 17 — God-Level Notes (Notebook)

## Why derived query methods?

`findAll()` + Java `filter` → every row into JVM, filter in memory.

Bad at scale: memory, network, CPU waste. DB can't filter early.

**Derived query method** = empty repository method whose **name** describes the query. Spring Data parses name at startup → Hibernate runs SQL. No SQL/JPQL from you for simple reads.

```text
findByDepartment_Name("IT")
  → Spring Data parses method name
  → Hibernate: JOIN + WHERE department.name = ?
```

---

## Who generates what?

```text
Method name  →  Spring Data (parse + build query definition)
SQL          →  Hibernate (generate + execute)
```

Wrong method name → **`QueryCreationException` at startup** (before app serves traffic).

---

## Master pattern (grammar)

```text
[Subject] + By + [Predicate] + [OrderBy]
```

| Part | Meaning |
|------|---------|
| **Subject** | operation (`find`, `exists`, `count`, …) + optional `Distinct` / `FirstN` / `TopN` |
| **By** | required before first property |
| **Predicate** | property + keyword conditions |
| **OrderBy** | sort — **always last** |

Example breakdown:

```text
findTop3ByDepartment_NameAndSalaryGreaterThanOrderBySalaryDesc
     │   │         │              │                    └── OrderBy (last)
     │   │         │              └── keyword + property
     │   │         └── nested property (relationship)
     │   └── limit 3
     └── select operation
```

---

## 1. Subject — operation prefix

Must include **`By`** before the first property name.

| Prefix | Meaning | Return type |
|--------|---------|-------------|
| `find…By` / `read…By` / `get…By` / `query…By` / `search…By` | select rows | `List`, `Set`, `Optional`, entity |
| `stream…By` | stream rows | `Stream<Entity>` |
| `count…By` | count matches | `long` |
| `exists…By` | any row exists? | `boolean` |
| `delete…By` / `remove…By` | delete matches | `long` or `void` |

```java
List<EmployeeEntity> findByName(String name);
long countByDepartment_Name(String dept);
boolean existsByEmail(String email);
```

### Subject modifiers (optional, before `By`)

| Modifier | SQL |
|----------|-----|
| `Distinct` | `DISTINCT` |
| `First` / `FirstN` / `Top` / `TopN` | limit rows |

```java
EmployeeEntity findFirstByOrderBySalaryDesc();
List<EmployeeEntity> findTop3ByDepartment_NameOrderBySalaryDesc(String dept);
```

---

## 2. Predicate — property + keyword

### Property path rules

- Property = **Java field name** on entity — **not** DB column name.
- Navigate relationships with **`_`** or **camelCase**:

```java
findByDepartment_Name(...)   // employee.department.name  ✅
findByDepartmentName(...)    // same path               ✅
findByDep_Name(...)          // no field dep              ❌ QueryCreationException
```

- **No keyword** after property = equality (`=`):

```java
findByName(String name)                         // WHERE name = ?
findByEmailAndDepartment_Name(String e, String d) // AND
findByNameOrEmail(String name, String email)    // OR
```

- **Parameter order** = order conditions appear in method name.
- **`And` / `Or`** chain multiple conditions.

### All keyword categories

| Category | Keywords | SQL |
|----------|----------|-----|
| Logic | `And`, `Or` | `AND`, `OR` |
| Compare | `LessThan`, `LessThanEqual`, `GreaterThan`, `GreaterThanEqual`, `Between` | `<`, `<=`, `>`, `>=`, `BETWEEN` |
| Date | `After`, `Before` | date compare |
| Null | `IsNull`, `IsNotNull` | `IS NULL`, `IS NOT NULL` |
| Boolean | `True`, `False` | `= true/false` |
| String | `Like`, `NotLike`, `Containing`, `NotContaining`, `StartingWith`, `EndingWith`, `IgnoreCase` | `LIKE` patterns / case |
| Collection | `In`, `NotIn` | `IN (...)`, `NOT IN` |

String notes:

| Keyword | Pattern |
|---------|---------|
| `Containing` | `%value%` (Spring adds `%`) |
| `StartingWith` | `value%` |
| `EndingWith` | `%value` |
| `Like` | you pass `%` yourself |
| `IgnoreCase` | suffix on string property |

```java
findByNameContainingIgnoreCase("test")   // UPPER(name) LIKE '%test%'
findBySalaryBetween(min, max)
findByDepartment_NameIn(List<String> names)
findByDepartmentIsNull()
```

### OrderBy (always at end)

```java
findByDepartment_NameOrderBySalaryDesc(String dept)
findByDepartment_NameOrderByNameAscSalaryDesc(String dept)
```

`ORDER BY` goes in **SQL**, not Java sort.

---

## 3. Return type rules

| Return | Use when |
|--------|----------|
| `List<Entity>` | 0..N rows |
| `Optional<Entity>` | 0 or 1 row (`findByEmail`) |
| `Entity` | exactly 1 row expected (exception if 0 or many) |
| `boolean` | only with `exists…By` |
| `long` | only with `count…By` |
| `Page<Entity>` | + `Pageable` param (pagination — later) |

```java
Optional<EmployeeEntity> findByEmail(String email);
boolean existsByEmail(String email);   // lightweight — no full row load
```

Handle `Optional` with `isEmpty()` / `orElseThrow()` — not bare `.get()` in production.

Parameter count must match method name (`Between` = 2 params, two properties with `And` = 2 params, etc.).

---

## What we practiced (experiments)

| Method | SQL shape | Result |
|--------|-----------|--------|
| `findByDepartment_Name("IT")` | JOIN + WHERE dept name | IT employees |
| `findByName("Test")` | exact match | 0 rows if not exact string |
| `findBySalaryGreaterThan(200)` | `salary > ?` | filtered in DB |
| `findByNameContainingIgnoreCase("test")` | `UPPER LIKE` | Vijendra Test, Test |
| `findByDepartment_NameAndSalaryGreaterThan` | JOIN + AND | salary filter in DB |
| `findByDepartment_NameOrderBySalaryDesc` | JOIN + ORDER BY | highest salary first |
| `findByEmail` | `Optional` | present or empty |
| `existsByEmail` | existence check | boolean |

---

## Relationship queries + JOIN

`findByDepartment_Name` → SQL **JOINs** `departments` for **WHERE filter**.

JOIN in derived query = **filter**. Not the same as **fetch join** (load association into memory).

Stale string column `employees.department` can disagree with `department_id` — JPA uses **`department_id` / relationship**.

---

## EAGER vs LAZY + derived queries (Day 16 link)

| Setup | `getName()` only | + `getDepartment()` inside `@Transactional` |
|-------|------------------|-----------------------------------------------|
| `@ManyToOne(EAGER)` | often **2 queries** (main + dept SELECT) | 2+ |
| `@ManyToOne(LAZY)` | **1 query** | 2+ on touch |

EAGER extra query ≠ Spring Data bug. JOIN filters; EAGER **hydrates** association separately.

**Production:** `@ManyToOne(LAZY)`. Touch lazy associations inside **`@Transactional` service**. Return DTO / int / String — not detached entities with lazy proxies.

**N+1:** loop many employees + `getDepartment()` → many dept SELECTs. Fix: **`@Query` + JOIN FETCH** (Day 18).

---

## Mental model

```text
EmployeeRepository method name
↓
Spring Data proxy (parsed at startup)
↓
JPQL/SQL via Hibernate
↓
MySQL
```

---

## Hard rules (memorize)

1. Property path = **Java entity fields**, not DB columns.
2. Wrong field → **`QueryCreationException`** at startup.
3. `_` or camelCase navigates relationships (`Department_Name`).
4. **`OrderBy` always last** in method name.
5. Filter in **DB** — never `findAll()` + stream filter for simple queries.
6. **`existsBy`** for boolean checks; **`Optional`** for 0/1 row.
7. Name too long / need fetch join → **`@Query` JPQL** (Day 18).

---

## What's next (not Day 17)

| Topic | Day |
|-------|-----|
| `@Query` / JPQL | **18** |
| `JOIN FETCH` / N+1 fix | **18** |
| Native SQL | 18+ |
| `open-in-view` | **19** |
| Specifications / Criteria | much later |

Day 17 = grammar + common keywords + observe SQL.  
Day 18 = when derived names are not enough.

---

# Day 18 — JPQL & @Query ✅ DONE

## Day 18 Objective

Connect:

```text
Day 17 — derived query methods (name → SQL)
        ↓
Day 18 — @Query + JPQL (you write the query when names fail)
```

Core question:

> **When is a derived method name not enough, and how do I write JPQL with @Query — including JOIN FETCH?**

In scope:

- WHY `@Query` (long names, fetch joins, custom logic)
- JPQL vs SQL vs entity names
- `@Query` on repository methods
- Named parameters (`:name`, `@Param`)
- `JOIN FETCH` — fix N+1 / load association in one query
- Observe SQL with `show-sql`
- When to stay with derived methods vs `@Query`

Out of scope (Day 19):

- Full open-in-view deep dive
- Native SQL as main focus (mention only)
- Specifications / Criteria

Do not start experiments until Jira ticket exists.

Jira ticket created.

---

# Day 18 Experiment 1 — WHY @Query? ✅ DONE

- Derived = filter only; JOIN FETCH = load strategy. Cannot replace one with the other.
- @Query when: naming can't express it (JOIN FETCH, aggregates) OR readability.
- JPQL = entity class + field names, not table/column names.

# Day 18 Experiment 2 — first @Query + @Param ✅ DONE

- `findEmployeesByDepartmentNameAndSalaryAbove` in EmployeeRepository (text block JPQL).
- JPQL needs alias (`e`), not SQL `*`.
- EAGER demo: 2 employees same dept → 1 dept SELECT; 2nd from PC (same `@25c887ca` ref).

# Day 18 Experiment 3 — JOIN FETCH vs N+1 ✅ DONE

- Reverted `@ManyToOne(LAZY)` on `EmployeeEntity.department`.
- Same `@Query` method upgraded with `JOIN FETCH e.department d`.
- Observed **1 SQL** — SELECT includes both employee + department columns in one JOIN.
- Loop `getDepartment().getName()` → **0 extra SELECTs** (N+1 fixed).
- Compare: derived / plain JPQL JOIN = filter only; EAGER = separate dept SELECT(s); JOIN FETCH = load in one trip.

---

# Day 18 — God-Level Notes (Notebook)

## Why `@Query`?

Derived methods = **name → query** for simple reads. Stop when:

1. Spring **cannot name it** — `JOIN FETCH`, aggregates (`COUNT`, `AVG`), subqueries, updates.
2. Method name is **unreadable** — same logic clearer in JPQL.

Derived `findByDepartment_Name` = **WHERE filter** (JOIN for condition). It does **not** fetch association into memory. LAZY + loop → **N+1**.

---

## JPQL vs SQL

| JPQL | SQL |
|------|-----|
| `EmployeeEntity` (class) | `employees` (table) |
| `e.department.name` (field path) | `department_id`, `departments.name` |
| `SELECT e FROM … e` (alias required) | `SELECT *` OK in SQL |

JPQL = **object model language**. Hibernate translates to SQL.

Invalid: `SELECT * FROM EmployeeEntity` → grammar exception. Use alias: `SELECT e FROM EmployeeEntity e`.

---

## `@Query` + `@Param`

```java
@Query("""
    SELECT e FROM EmployeeEntity e
    WHERE e.department.name = :deptName AND e.salary > :minSalary
    """)
List<EmployeeEntity> findEmployeesByDepartmentNameAndSalaryAbove(
    @Param("deptName") String deptName,
    @Param("minSalary") BigDecimal minSalary);
```

- `:deptName` = named bind parameter in JPQL.
- `@Param("deptName")` = maps method argument to JPQL name (required when names differ or for clarity).
- Text blocks (`"""`) = readable multi-line JPQL.

---

## JOIN vs JOIN FETCH

| Kind | Purpose | After query |
|------|---------|-------------|
| **JOIN** (in derived or plain `@Query`) | Filter (`WHERE dept.name = ?`) | Association still **LAZY** |
| **JOIN FETCH** | **Eager load in same query** | Association **initialized** in PC |

```java
@Query("""
    SELECT e FROM EmployeeEntity e
    JOIN FETCH e.department d
    WHERE d.name = :deptName AND e.salary > :minSalary
    """)
```

Observed SQL (1 query):

```sql
SELECT ee.id, d.id, d.location, d.name, ee.email, ee.name, ee.salary
FROM employees ee
JOIN departments d ON d.id = ee.department_id
WHERE d.name = ? AND ee.salary > ?
```

Loop `getDepartment().getName()` → no extra SELECTs.

---

## N+1 fix decision tree

```text
Need association in loop?
├─ Stay LAZY (default)
├─ DON'T use global EAGER on @ManyToOne (over-fetch, still N+1 across different FKs)
├─ DON'T rely on derived findByDepartment_Name alone (filter ≠ fetch)
└─ DO use @Query + JOIN FETCH on the read that needs the graph
```

| Approach | Queries (2 IT employees, need dept name) |
|----------|------------------------------------------|
| `@Query` JOIN only + LAZY + loop | 1 + N dept SELECTs (N+1) |
| `@ManyToOne(EAGER)` + derived/@Query | 1 main + 1+ dept SELECT(s) |
| `@Query JOIN FETCH` + LAZY | **1 total** ✅ |

---

## Persistence context bonus (EAGER experiment)

Two employees, **same** `department_id`:

- EAGER loads department once → 1 dept SELECT.
- Second `getDepartment()` → **same** `DepartmentEntity` instance from PC (identity map) — no 2nd SELECT.
- Different department IDs with EAGER → typically **one SELECT per distinct id** (N+1 pattern).

JOIN FETCH avoids this entirely by loading graph in one query.

---

## When to use what

| Situation | Tool |
|-----------|------|
| Simple filter/sort/exists | Derived method |
| Same filter, clearer JPQL | `@Query` |
| Need association in same tx / loop | `@Query JOIN FETCH` |
| Complex report / aggregate | `@Query` (later: native SQL) |

Keep **`@ManyToOne(LAZY)`** in production. Fetch explicitly where needed.

---

## Mental model

```text
Repository @Query JPQL
  ↓
Spring Data parses + binds @Param
  ↓
Hibernate → SQL (show-sql)
  ↓
Persistence context (managed entities, identity map)
  ↓
Loop safe if JOIN FETCH loaded the association inside tx
```

---

## Hard rules (memorize)

1. JPQL uses **entity + field names**, not table/column names.
2. `SELECT e FROM Entity e` — alias required; no SQL `*`.
3. Derived JOIN = **filter**; `JOIN FETCH` = **load strategy**.
4. **N+1 fix** = `JOIN FETCH` on the query that returns the list, not global EAGER.
5. PC dedupes by **entity id** — same dept loaded once can serve many employees (still prefer JOIN FETCH for one round trip).
6. **`@Transactional` service** — touch lazy/fetched associations inside tx; return DTOs to controller.

---

## What's next (Day 19)

| Topic | Why |
|-------|-----|
| **`spring.jpa.open-in-view`** | Boot enables by default — lazy loads can happen outside your `@Transactional` service (you saw the WARN in logs) |
| When to disable OSIV | Production best practice |

Day 18 = write JPQL when names fail + **JOIN FETCH** kills N+1.  
Day 19 = where transactions **end** in a web app.

Ready for day review → God-level notes → Jira Done.

---

# Day 19 — Open Session In View (OSIV) ✅ DONE

## Day 19 Objective

Connect:

```text
Day 16 — LAZY associations + LazyInitializationException
Day 18 — load graph inside tx (JOIN FETCH)
        ↓
Day 19 — spring.jpa.open-in-view (where the session actually ends in a web app)
```

Core question:

> **Why does lazy loading sometimes work in a controller even when my service has no `@Transactional` — and why do teams disable OSIV in production?**

In scope:

- WHAT is Open Session In View (OSIV)
- Boot default: `spring.jpa.open-in-view=true` + startup WARN
- Request lifecycle: filter opens session → controller → JSON → session closes
- `@Transactional` service boundary vs OSIV session boundary
- Experiment: OSIV on → lazy works outside service tx
- Experiment: OSIV off → `LazyInitializationException`
- Fix: `@Transactional` read + map to DTO **inside** service (or JOIN FETCH); return DTO only
- Production recommendation: disable OSIV, explicit fetch in service layer

Out of scope (later):

- `@EntityGraph` (mention only)
- Full MVC/view rendering (Thymeleaf) — same OSIV idea applies
- Performance tuning beyond N+1 awareness

Do not start experiments until Jira ticket exists.

Jira ticket: created.

**Note:** User hit `LazyInitializationException` in `JpaLearningRunner` + `employeeService.getEmployee()` — OSIV does **not** apply outside HTTP requests.

---

# Day 19 Experiment 1 — WHY OSIV? ✅ DONE

- OSIV = web filter keeps EntityManager open for **whole HTTP request** (Boot default `true`).
- `@Transactional` = business tx boundary on a **method** — not the same as OSIV.
- Runner/batch = no OSIV → lazy touch needs `@Transactional` or JOIN FETCH in service.

---

# Day 19 Experiment 2 — OSIV on, HTTP GET ✅ DONE

- Postman `GET /employees/{id}` with dept in `toEmployee()` → **works** (OSIV session open during service mapping).
- Same code via `JpaLearningRunner` → **LazyInitializationException** (no OSIV).

---

# Day 19 Experiment 3 — OSIV off ✅ DONE

- `spring.jpa.open-in-view=false` in `application.properties`.
- Same Postman call, dept touch in service **without** `@Transactional` → **LazyInitializationException** (expected).

---

# Day 19 Experiment 4 — Fix: @Transactional on read ✅ DONE

- `@Transactional` on `getEmployee()` → lazy `getDepartment().getName()` inside service → **works with OSIV off**.
- Production pattern: OSIV off + explicit `@Transactional(readOnly=true)` read + map to DTO inside service.

---

# Day 19 — God-Level Notes (Notebook)

## What is OSIV?

**Open Session In View** — Spring opens an `EntityManager` at the **start** of an HTTP request and closes it when the **response finishes**.

```text
HTTP request in
  → [OSIV opens EntityManager]     ← Boot default: spring.jpa.open-in-view=true
  → Controller → Service → Repository
  → lazy getDepartment() can work here (session still open)
  → JSON / view rendering
  → [OSIV closes EntityManager]
HTTP response out
```

Enabled automatically by Spring Boot. Not an annotation you write — a **servlet filter** (`OpenEntityManagerInViewFilter`).

Startup WARN = reminder that DB access can happen **after** your service returns (during JSON/view).

---

## OSIV vs @Transactional

| | OSIV | `@Transactional` |
|---|------|------------------|
| Scope | Entire **HTTP request** | One **method** (or class) |
| Applies to | Web requests only | Any Spring bean call |
| Purpose | Keep session for late lazy loads | Unit of work, commit/rollback |
| You configure | `spring.jpa.open-in-view` | `@Transactional` on service |

With **OSIV on**: lazy load can work even without service `@Transactional` (during HTTP only).

With **OSIV off**: lazy associations must be touched **inside** a `@Transactional` service method (or fetched via JOIN FETCH / DTO mapping there).

---

## What we proved (experiments)

| Scenario | Result |
|----------|--------|
| OSIV **on** + Postman GET + lazy dept in service | ✅ Works |
| OSIV **on** + `JpaLearningRunner` | ❌ LazyInitializationException |
| OSIV **off** + Postman GET + no `@Transactional` | ❌ LazyInitializationException |
| OSIV **off** + `@Transactional` on `getEmployee()` | ✅ Works |

**Runner lesson:** OSIV never runs for `CommandLineRunner` — always use `@Transactional` or JOIN FETCH in service for lazy data.

---

## Why disable OSIV in production REST APIs?

1. **Hides missing fetch/DTO work** — lazy SQL fires in controller/JSON phase, hard to debug.
2. **N+1 surprises** — queries during "view" / serialization, not in service layer.
3. **Longer DB connection** — held for full request, not just service time.
4. **False safety** — "works without `@Transactional`" only because OSIV masks the problem.

**Recommended stack (what you built today):**

```properties
spring.jpa.open-in-view=false
```

```java
@Transactional(readOnly = true)   // readOnly = hint, no unnecessary flush
public Employee getEmployee(int id) {
    EmployeeEntity e = employeeRepository.findById(id)...;
    // touch lazy fields HERE — or use JOIN FETCH query
    return toEmployee(e);   // plain Employee — no lazy proxies to caller
}
```

Return **DTOs / plain models** to controller — not entities with lazy proxies.

---

## Three boundaries to remember

```text
1. Repository tx     — short, per repo call (Spring Data default)
2. Service @Transactional  — where YOU load associations + map to DTO  ← production rule
3. OSIV session      — whole HTTP request (optional crutch, Boot default ON)
```

**Production:** rely on **#2**, disable **#3**, don't depend on **#1** alone for lazy graphs.

---

## Hard rules (memorize)

1. OSIV = **HTTP only** — not Runner, not `@Scheduled`, not message listeners.
2. `@Transactional` on service ≠ OSIV — different scope, different purpose.
3. **LAZY + OSIV off** → touch association **inside** `@Transactional` service.
4. **Best fix for lists** → `@Query JOIN FETCH` (Day 18) inside `@Transactional` read.
5. Keep `open-in-view=false` in production APIs; load explicitly in service layer.

---

## Follow-up in your codebase

- `getAllEmployees()` still has **no** `@Transactional` — with OSIV off + dept in `toEmployee()`, `GET /employees` will fail same way. Apply same fix (or JOIN FETCH list query).
- Line 53 `getDepartment().getName()` before `toEmployee()` is redundant once `toEmployee()` maps dept — fine for experiment, can remove duplicate touch.

---

## What's next (Day 20+)

| Topic | Why |
|-------|-----|
| Pagination (`Pageable`) | `findAll()` doesn't scale |
| `@EntityGraph` | alternative to JOIN FETCH for fetch plans |
| Native SQL `@Query(nativeQuery=true)` | when JPQL isn't enough |

Day 19 = **where the session ends** in a web app.  
Day 18 + 19 together = **JOIN FETCH in service** + **OSIV off** + **@Transactional reads** = production JPA read pattern.

**readOnly quiz (post-Day 19):** still opens tx/session; don't call save(); lazy fix = `@Transactional` itself, readOnly = optimization hint.

Ready for day review → God-level notes → Jira Done.

---

# Day 20 — Pagination (`Pageable`) ✅ DONE

## Day 20 Objective

Connect:

```text
Day 17 — derived queries return List (all matching rows)
Day 19 — @Transactional read + OSIV off
        ↓
Day 20 — Pageable / Page — fetch one page at a time from DB
```

Core question:

> **How do I return employees page-by-page instead of loading the entire table with `findAll()`?**

In scope:

- WHY pagination (memory, network, UX, N+1 on lists)
- `Pageable`, `PageRequest.of(page, size, sort)`
- `Page<T>` vs `List<T>` — content + metadata (`totalElements`, `totalPages`, …)
- Controller query params: `?page=0&size=10&sort=salary,desc`
- `@Transactional(readOnly = true)` on paginated read service
- Observe SQL: `LIMIT` / `OFFSET` (MySQL)

Out of scope (later):

- Keyset / cursor pagination
- `Slice` vs `Page` deep dive (mention only)
- `@EntityGraph` / native SQL

Do not start experiments until Jira ticket exists.

Jira ticket: _(pending)_

---

# Day 20 Experiment 1 — WHY pagination? ✅ DONE

- Q1: findAll() at scale → memory, network, slow API, JVM pressure, N+1 risk on lists. ✅
- Q2: SQL uses LIMIT/OFFSET; Spring HTTP params = `page`, `size`, `sort` (page is 0-based). Refined.
- Q3: Page metadata = totalElements, totalPages, number, size, first/last, etc. — not "offset" in API. Refined.

# Day 20 Experiment 2 — first Pageable in service ✅ DONE

- `getEmployees(Pageable)` → `employeeRepository.findAll(pageable)`.
- Runner: `PageRequest.of(0, 2, Sort.by("name").descending())`.
- Observed: `totalElements=3`, `totalPages=2`, 2 SQL (data LIMIT + count).
- Note: returns `Page<EmployeeEntity>` — map to DTO in Experiment 3/4.

# Day 20 Experiment 3 — Pageable in controller (HTTP) ✅ DONE

- `GET /employees?page=&size=&sort=` → Spring binds `Pageable` automatically.
- Controller: `Page<EmployeeResponse>` via `employeeService.getEmployees(pageable).map(toEmployee)`.
- Service: `@Transactional(readOnly=true)` + `Page<Employee>` + `.map(toEmployee)`.
- Postman verified: LIMIT/OFFSET + count query; sort via `sort=id,desc`.
- WARN: serializing `PageImpl` directly — stable JSON needs custom wrapper or `@EnableSpringDataWebSupport` (park for later).

---

# Day 20 — God-Level Notes (Notebook)

## Why pagination?

`findAll()` → every row into JVM + huge JSON. Bad for memory, network, UX, and N+1 on associations.

**Pagination** = DB returns one **page** (`LIMIT` / `OFFSET`) + optional **total count**.

---

## Core types (input → output)

| Type | Role | Package |
|------|------|---------|
| **`Pageable`** | Input: page, size, sort | `org.springframework.data.domain` |
| **`PageRequest`** | Build `Pageable` in code | same |
| **`Sort`** | Order by entity fields | same |
| **`Page<T>`** | Output: content + metadata | same |
| **`Slice<T>`** | Output: content + hasNext only (no total count) | same |

```text
Pageable (input)  →  repository.findAll(pageable)  →  Page<T> (output)
```

---

## PageRequest cheat sheet

```java
PageRequest.of(0, 10)                                    // page 0, size 10
PageRequest.of(1, 10, Sort.by("salary").descending())
PageRequest.of(0, 5, Sort.by("department.name").asc())   // nested property
```

**Page is 0-based:** `page=0` = first page. `OFFSET = page × size`.

---

## HTTP → Pageable (no manual parsing)

```java
@GetMapping
public Page<EmployeeResponse> getEmployees(Pageable pageable) { ... }
```

```text
GET /employees?page=0&size=10&sort=salary,desc&sort=name,asc
```

| Param | Maps to |
|-------|---------|
| `page` | page number (0-based) |
| `size` | page size |
| `sort=field,dir` | `Sort` (repeat for multiple) |

Defaults (if omitted): typically `page=0`, `size=20`.

---

## Repository (free from JpaRepository)

```java
Page<EmployeeEntity> findAll(Pageable pageable);

// Derived + pagination — Pageable LAST:
Page<EmployeeEntity> findByDepartment_Name(String name, Pageable pageable);
List<EmployeeEntity> findBySalaryGreaterThan(BigDecimal min, Pageable pageable);
// List + Pageable → paginated SQL but NO total metadata
```

---

## SQL behind Page<T>

Usually **2 queries**:

```sql
SELECT count(*) FROM employees ...;           -- totalElements, totalPages
SELECT ... FROM employees ... ORDER BY ... LIMIT ? OFFSET ?;
```

---

## Service pattern (production)

```java
@Transactional(readOnly = true)
public Page<Employee> getEmployees(Pageable pageable) {
    return employeeRepository.findAll(pageable)
            .map(this::toEmployee);   // map content; keep page metadata
}
```

- **`readOnly = true`** on reads.
- **`.map()`** on `Page` transforms rows, preserves `totalElements`, etc.
- Map lazy associations **inside** this method (OSIV off).

---

## Controller pattern

```java
@GetMapping
public Page<EmployeeResponse> getEmployees(Pageable pageable) {
    return employeeService.getEmployees(pageable).map(this::toEmployee);
}
```

Return **`Page<DTO>`** — not `Page<Entity>` to JSON.

---

## Page metadata (what client gets)

| Field | Meaning |
|-------|---------|
| `content` | Rows on this page |
| `totalElements` | All rows in DB |
| `totalPages` | `ceil(totalElements / size)` |
| `number` | Current page (0-based) |
| `size` | Page size |
| `first` / `last` | Boundary flags |

---

## Page vs Slice

| | `Page<T>` | `Slice<T>` |
|---|-----------|------------|
| Total count | ✅ `SELECT COUNT(*)` | ❌ |
| Total pages | ✅ | ❌ |
| Next page? | ✅ | ✅ `hasNext()` |
| SQL cost | 2 queries | 1 query |

Use **`Page`** for "Page X of Y". Use **`Slice`** for infinite scroll.

---

## Pagination + lazy associations (N+1 per page)

Paginating **does not** fix N+1. Each row on the page that calls `getDepartment()` in `toEmployee()` can trigger a dept SELECT.

**Fix for list reads:** `@Query JOIN FETCH` (Day 18) on a paginated query — advanced; `@EntityGraph` later.

---

## PageImpl JSON warning (Boot 3+/4)

Returning `Page` directly may log:

```text
Serializing PageImpl instances as-is is not supported...
```

For stable API contracts, wrap in a custom DTO later (`PagedResponse { content, totalElements, ... }`) or enable Spring Data web support. Fine for learning.

---

## Hard rules (memorize)

1. **`Pageable` = in**, **`Page` = out** (with metadata).
2. **`page` is 0-based** in Spring (HTTP and code).
3. **`Page` ≈ 2 SQL** — count + data.
4. Put **`Pageable` last** on repository methods.
5. **`@Transactional(readOnly=true)`** on paginated reads that touch lazy fields.
6. Pagination limits rows — **JOIN FETCH** still needed to kill N+1 within a page.

---

## What's next (Day 21+)

| Topic | Why |
|-------|-----|
| Custom paged response DTO | stable JSON, hide PageImpl |
| `@EntityGraph` | fetch plan without JPQL JOIN FETCH |
| Native SQL `@Query` | reports / DB-specific SQL |
| Keyset pagination | OFFSET slow on huge tables |

Day 20 = stop using unbounded `findAll()` on list APIs.

Ready for day review → God-level notes → Jira Done.

---

# Day 21 — `@EntityGraph` (Fetch Plans) ✅ DONE

## Day 21 Objective

Connect:

```text
Day 18 — JOIN FETCH in JPQL (explicit fetch in query string)
Day 20 — paginated list + lazy dept → N+1 per page
        ↓
Day 21 — @EntityGraph (declarative fetch plan on repository method / entity)
```

Core question:

> **How do I fetch `department` with employees without writing JOIN FETCH JPQL — especially on `findAll(Pageable)`?**

In scope:

- WHY `@EntityGraph` (reuse fetch plan, works with derived methods + Pageable)
- `@NamedEntityGraph` on entity + `@EntityGraph` on repository
- Inline `@EntityGraph(attributePaths = {"department"})`
- `EntityGraphType.FETCH` vs `LOAD` (FETCH default on queries)
- Compare SQL / query count vs plain `findAll` + N+1
- Fix paginated `getEmployees` N+1

Out of scope (Day 22+):

- Native SQL `@Query(nativeQuery = true)`
- Custom paged response DTO (PageImpl warning)
- Keyset / cursor pagination

Do not start experiments until Jira ticket exists.

Jira ticket: _(pending)_

---

# Day 21 Experiment 1 — WHY @EntityGraph? ✅ DONE

- Q1: toEmployee touches lazy department → N+1 extra SELECTs per page row. ✅
- Q2: @EntityGraph on repo — no custom JPQL JOIN FETCH needed; works with findAll(Pageable). ✅
- Q3: attributePaths = Java entity field name (relationship), not table/column. ✅

# Day 21 Experiment 2 — @NamedEntityGraph on entity ✅ DONE

- `@NamedEntityGraph(name = "Employee.withDepartment", attributeNodes = department)` on EmployeeEntity.

# Day 21 Experiment 3 — @EntityGraph on repository ✅ DONE

- Gotcha: custom name `findAllWithDepartment` without `@Query` → derived parse fails → `QueryCreationException`.
- Fix options: `@Query` + `@EntityGraph`, or override `findAll(Pageable)` with `@EntityGraph`.
- Final: override `findAll(Pageable)` + inline `@EntityGraph(attributePaths = {"department"})`.
- Postman: 1 data query with LEFT JOIN departments + 1 count query; no N+1 dept SELECTs.

# Day 21 Experiment 4 — inline vs named graph ✅ DONE

- Named: `@NamedEntityGraph` on entity + `@EntityGraph(value = "Employee.withDepartment")`.
- Inline: `@EntityGraph(attributePaths = {"department"})` — no entity annotation needed.
- Same SQL shape — JOIN in one query.

---

# Day 21 — God-Level Notes (Notebook)

## Why @EntityGraph?

Day 20 paginated list + `toEmployee()` touching lazy `department` → N+1 per page.

Day 18 fix = JOIN FETCH in JPQL. Day 21 fix = **declarative fetch plan** on repository — especially for `findAll(Pageable)` without writing JPQL.

---

## Two ways to fetch association in one query

| Approach | Where | Best for |
|----------|-------|----------|
| **JOIN FETCH** | Inside `@Query` JPQL | Complex filters, custom JPQL |
| **@EntityGraph** | On repository method (+ optional `@NamedEntityGraph` on entity) | `findAll(Pageable)`, derived methods, reusable fetch plans |

Both → Hibernate adds JOIN → department loaded in same data query.

---

## @NamedEntityGraph (on entity)

```java
@NamedEntityGraph(
    name = "Employee.withDepartment",
    attributeNodes = @NamedAttributeNode("department")
)
public class EmployeeEntity { ... }
```

Reuse on many repo methods:

```java
@EntityGraph(value = "Employee.withDepartment")
Page<EmployeeEntity> findAll(Pageable pageable);
```

---

## Inline @EntityGraph (on repository — no entity annotation)

```java
@EntityGraph(attributePaths = {"department"})
Page<EmployeeEntity> findAll(Pageable pageable);
```

`attributePaths` = **Java field names** (relationship), not table/column names.

---

## @EntityGraph does NOT define the base query

Two jobs:

1. **Base query** — `@Query`, valid derived name, or override known method (`findAll(Pageable)`)
2. **Fetch plan** — `@EntityGraph` adds association load to that query

Custom method name like `findAllWithDepartment` without `@Query` → Spring tries derived parse → fails (not valid Subject + By + property grammar).

**Valid without @Query:**

```java
@Override
@EntityGraph(attributePaths = {"department"})
Page<EmployeeEntity> findAll(Pageable pageable);
```

```java
@EntityGraph(attributePaths = {"department"})
Page<EmployeeEntity> findBySalaryGreaterThan(BigDecimal min, Pageable pageable);
```

---

## EntityGraphType (FETCH vs LOAD)

| Type | Behavior |
|------|----------|
| **FETCH** (default on queries) | JOIN — load in same query ✅ fix N+1 |
| **LOAD** | Separate SELECT when accessed — can still N+1 |

Use **FETCH** (default) for read APIs.

---

## Observed SQL (paginated list)

```sql
-- data (JOIN from entity graph)
SELECT ee..., d... FROM employees ee LEFT JOIN departments d ... LIMIT ? OFFSET ?

-- metadata for Page<T>
SELECT count(*) FROM employees
```

No `SELECT ... FROM departments WHERE id=?` per row.

---

## Decision tree (production reads)

```text
Paginated / list API needs association?
├─ Simple findAll / derived filter → @EntityGraph(attributePaths = {...})
├─ Complex JPQL → @Query + JOIN FETCH
├─ @Transactional(readOnly=true) on service
├─ Map to DTO inside service (OSIV off)
└─ Stay @ManyToOne(LAZY) on entity
```

---

## Hard rules (memorize)

1. `@EntityGraph` = fetch plan; still need valid base query (override, derived, or `@Query`).
2. Invalid derived names fail at **startup** — same as Day 17.
3. **Named graph** = reuse; **attributePaths** = inline/simple.
4. **JOIN FETCH** and **@EntityGraph FETCH** — same goal, different style.
5. Pagination limits rows; **EntityGraph/JOIN FETCH** fixes N+1 **within** the page.

---

## What's next (Day 22+)

| Topic | Why |
|-------|-----|
| Native SQL `@Query(nativeQuery=true)` | DB-specific reports |
| Custom paged response DTO | stable JSON (PageImpl warning) |
| Keyset pagination | OFFSET slow at huge scale |

Day 21 = declarative fetch plans without JOIN FETCH JPQL.

Ready for day review → God-level notes → Jira Done.

---

# Day 22 — Native SQL (`nativeQuery = true`) ✅ DONE

## Day 22 Objective

Connect:

```text
Day 18 — @Query + JPQL (entity/field names)
Day 21 — @EntityGraph (fetch plans)
        ↓
Day 22 — @Query(nativeQuery = true) — real SQL, table/column names
```

Core question:

> **When is JPQL not enough, and how do I run database SQL from a Spring Data repository?**

In scope:

- WHY native SQL (DB functions, reports, legacy SQL, DB-specific syntax)
- JPQL vs Native SQL — naming rules
- `@Query(value = "...", nativeQuery = true)` + `@Param`
- Return `List<EmployeeEntity>` from native SELECT
- Native JOIN across `employees` + `departments`
- Observe SQL in logs (no JPQL translation layer for the query body)

Out of scope (later):

- `SqlResultSetMapping` / interface projections for non-entity columns
- Native query pagination (`countQuery` attribute)
- Stored procedures

Do not start experiments until Jira ticket exists.

Jira ticket: _(pending)_

---

# Day 22 Experiment 1 — WHY Native SQL? ✅ DONE

- Q1: Native uses table names (`employees`) and SQL column paths (`d.name`), not entity class/field names. ✅ (refine: `d.name` = departments.name column via alias `d`, not entity field path)
- Q2: Legacy/trusted SQL; complex reports; DB-specific functions. ✅
- Q3: `nativeQuery=true` → SQL sent as-is to DB, no JPQL→SQL translation. ✅

# Day 22 Experiment 2 — first native query ✅ DONE

- `findBySalaryGreaterThanNative` — `SELECT * FROM employees WHERE salary > :minSalary`, nativeQuery=true.
- SQL in logs matches written SQL exactly (+ Spring wraps count for Page).
- User wired to `getEmployees` with Pageable — filters salary > 100 (note: changed list API behavior).
- Partial SELECT → Hibernate maps present columns; missing → null/0; risky for full entity — prefer SELECT * or DTO projection.

# Day 22 Experiment 3 — native JOIN with departments ✅ DONE

- `findHighEarnersInDeptNative` — table/column JOIN on `department_id`; SQL as-is in logs.
- Q: native needs `department_id` in JOIN; JPQL uses object path `e.department`. ✅
- No extra dept SELECTs in runner because only `e.getName()` — did NOT touch lazy `getDepartment()`.
- Native JOIN + `SELECT e.*` = filter only (like derived JOIN), NOT fetch join — would N+1 if toEmployee() ran.

---

# Day 22 — God-Level Notes (Notebook)

## Why Native SQL?

When JPQL/derived/EntityGraph can't express it — DB functions, legacy SQL, complex reports, MySQL-specific syntax.

**Default order:** derived → JPQL → EntityGraph → **native SQL last**.

---

## JPQL vs Native

| | JPQL | Native (`nativeQuery=true`) |
|---|------|----------------------------|
| Names | `EmployeeEntity`, `e.department.name` | `employees`, `department_id`, `d.name` |
| Portable | Yes | No — tied to MySQL |
| SQL in logs | Translated from JPQL | **Exactly what you wrote** |
| `@Param` | `:name` | `:name` (same) |

```java
@Query(value = "SELECT e.* FROM employees e WHERE e.salary > :min", nativeQuery = true)
List<EmployeeEntity> findBySalaryNative(@Param("min") BigDecimal min);
```

---

## Returning entities from native SQL

- **`SELECT *` / `SELECT e.*`** from mapped table → Hibernate maps to `EmployeeEntity` ✅
- **Partial columns** → missing fields = null/0; missing `id` = broken ❌
- **Non-entity columns** → use DTO/projection (later)

---

## Native JOIN ≠ fetch join

```sql
SELECT e.* FROM employees e
INNER JOIN departments d ON d.id = e.department_id
WHERE d.name = ?
```

JOIN here = **filter** (same as derived `findByDepartment_Name`).  
`SELECT e.*` only maps **employee columns** — `department` association stays **LAZY**.

Extra dept SELECTs happen **only when you call `getDepartment()`** (e.g. `toEmployee()`).

| Code in loop | Extra dept SELECTs? |
|--------------|---------------------|
| `e.getName()` only | **No** |
| `e.getDepartment().getName()` / `toEmployee()` | **Yes** (N+1) |

Native JOIN does **not** replace JOIN FETCH / EntityGraph for loading associations.

---

## Native + pagination

```java
@Query(value = "SELECT * FROM employees WHERE salary > :min", nativeQuery = true)
Page<EmployeeEntity> findBySalaryNative(@Param("min") BigDecimal min, Pageable pageable);
```

Spring wraps your SQL for **count** automatically. Optional explicit `countQuery = "..."` for complex SQL.

---

## Hard rules (memorize)

1. **`nativeQuery = true`** → SQL sent as-is to DB.
2. Native uses **table + column names**, not entity fields.
3. **`SELECT e.*`** for entity return type; partial columns → DTO.
4. Native JOIN filters; does **not** load association unless you fetch explicitly.
5. Prefer JPQL when possible; native when you **need** SQL power.

---

## Native N+1 fix (bonus experiment)

**Not EntityGraph** — use JOIN + flat projection:

```java
public interface EmployeeDeptSummary {
    Integer getId();
    String getName();
    BigDecimal getSalary();
    String getDeptName();  // alias deptName in SQL
}
```

```sql
SELECT e.id AS id, e.name AS name, e.salary AS salary, d.name AS deptName
FROM employees e
INNER JOIN departments d ON d.id = e.department_id
WHERE ...
```

Return `List<EmployeeDeptSummary>` or `Page<EmployeeDeptSummary>` — 1 data query, dept name in row, no `getDepartment()`.

---

## Native + Page — do NOT add LIMIT/OFFSET manually

```java
Page<EmployeeDeptSummary> find...(params, Pageable pageable);
```

- Pass **`Pageable`** as last param — Spring appends `LIMIT`/`OFFSET` (or dialect equivalent).
- **Do not** write `LIMIT :limit OFFSET :offset` in SQL when using `Pageable` — double pagination / conflict.
- **`countQuery`** — optional but recommended for JOINs:

```java
@Query(value = "SELECT e.id, ... JOIN ...", 
       countQuery = "SELECT count(*) FROM employees e JOIN ...",
       nativeQuery = true)
Page<EmployeeDeptSummary> find...(Pageable pageable);
```

Without `countQuery`, Spring may wrap: `select count(*) from (your full query) a_`.

---

Ready for day review → God-level notes → Jira Done.

---

# Day 23 — `@Modifying` Queries (UPDATE / DELETE) ✅ DONE

## Day 23 Objective

Connect:

```text
Days 17–22 — READ queries (derived, JPQL, EntityGraph, native, DTO)
        ↓
Day 23 — @Modifying + @Query — WRITE via repository (UPDATE / DELETE)
```

Core question:

> **When is `save()` / `deleteById()` not enough, and how do I run UPDATE/DELETE with `@Query` safely?**

In scope:

- WHY `@Modifying` (bulk update, conditional delete, one SQL round trip)
- `@Modifying` + `@Query` (JPQL UPDATE / DELETE)
- **`@Transactional` required** on service (or repo) for modifying queries
- Return type: `int` (rows affected) vs `void`
- `clearAutomatically` / `flushAutomatically` (basics)
- JPQL delete/update uses **entity names**, not table names
- Contrast: `save()` loads entity first; `@Modifying` runs SQL directly

Out of scope (later):

- Specifications / Criteria (dynamic queries)
- Custom paged response DTO
- Auditing (`@CreatedDate`)

In scope (also covered in notes):

- Derived `deleteBy…` / `removeBy…` (DELETE only — no derived UPDATE)
- Native `@Modifying` UPDATE / DELETE (`nativeQuery = true`)
- Full matrix: when to use each write style

Do not start experiments until Jira ticket exists.

Jira ticket: created.

---

# Day 23 Experiment 1 — WHY @Modifying? ✅ DONE

- Q1: bulk UPDATE — findAll+save = N round trips; @Modifying = 1 SQL. ✅
- Q2: no @Transactional → TransactionRequiredException; explained below.
- Q3: JPQL DELETE/UPDATE uses entity + field names (`EmployeeEntity`), not tables. ✅

# Day 23 Experiment 2 — first @Modifying UPDATE ✅ DONE

- `updateSalaryByDepartment` in repo + `@Transactional` in JPAEmployeeService.
- Observed: 1 UPDATE SQL with dept JOIN; rows updated = 3 for IT.
- Note: add `@Param` on repo params for clarity; first run failed without tx (expected).

# Day 23 Experiment 3 — @Modifying DELETE ✅ DONE

- `deleteByEmail` — JPQL DELETE + `@Param`; `@Transactional` on JPAEmployeeService.
- Observed: `delete from employees where email=?`; rows deleted = 1.

---

# Day 23 — God-Level Notes (Notebook)

## Master matrix — all UPDATE & DELETE options

| Style | UPDATE | DELETE | `@Modifying` | `@Transactional` | Names |
|-------|:------:|:------:|:------------:|:----------------:|-------|
| `save()` / `deleteById()` | ✅ | ✅ | No | Recommended | Entity in Java |
| Derived `deleteBy…` / `removeBy…` | ❌ | ✅ | No | **Required** | Entity fields |
| `@Modifying` + JPQL `@Query` | ✅ | ✅ | **Yes** | **Required** | Entity + fields |
| `@Modifying` + native `@Query` | ✅ | ✅ | **Yes** | **Required** | Tables + columns |

**No derived `updateBy…`** — naming grammar has no UPDATE subject. Bulk UPDATE = `@Modifying @Query` (JPQL or native) or load + `save()`.

---

## Four styles — when & how

**1. `save()` / `deleteById()`** — single entity, Java logic, dirty checking. Loads row first → bad for bulk.

**2. Derived `deleteByEmail(...)`** — simple DELETE, 1 SQL, no `@Query`. Return `void` or `long` (rows deleted). **DELETE only.**

**3. `@Modifying` + JPQL** — bulk UPDATE/DELETE. Entity names (`EmployeeEntity`, `e.department.name`). Default for bulk writes.

```java
@Modifying
@Query("UPDATE EmployeeEntity e SET e.salary = :salary WHERE e.department.name = :dept")
int updateSalaryByDepartment(@Param("dept") String dept, @Param("salary") BigDecimal salary);

@Modifying
@Query("DELETE FROM EmployeeEntity e WHERE e.email = :email")
int deleteByEmail(@Param("email") String email);
```

**4. `@Modifying` + native** — same as #3 but table/column names + `nativeQuery = true`. Use for legacy SQL / MySQL-specific syntax.

---

## `@Transactional` — why required for writes

UPDATE/DELETE change DB state → must **commit or rollback as a unit**. Without `@Transactional` on service → `TransactionRequiredException` for `@Modifying` and derived `deleteBy…`. SELECT often works without service tx (repo read-only tx).

---

## Decision tree

```text
Single entity + Java logic?     → findById + save()  OR  deleteById()
DELETE by simple field?         → deleteByEmail(...)  OR  @Modifying (same 1 SQL)
Bulk/conditional UPDATE?        → @Modifying JPQL (default)  OR  native if DB-specific
Always                          → @Transactional on service
```

---

## What we proved

| Exp | SQL | Result |
|-----|-----|--------|
| UPDATE by dept (JPQL) | `update employees join departments set salary=?` | 3 rows |
| DELETE by email (JPQL) | `delete from employees where email=?` | 1 row |

---

## Hard rules (memorize)

1. **UPDATE:** `save()` or `@Modifying @Query` — no derived `updateBy…`.
2. **DELETE:** `deleteById()` or `deleteBy…()` or `@Modifying @Query`.
3. **`@Modifying` + `@Transactional`** required for all `@Query` writes.
4. Return **`int`/`long`** = rows affected (0 = no match).
5. JPQL → entity names; native → table/column names.
6. Bulk → `@Modifying`; single row + logic → `save()`.

---

## Write toolkit (one view)

```text
UPDATE → save()  |  @Modifying JPQL  |  @Modifying native
DELETE → deleteById()  |  deleteBy…()  |  @Modifying JPQL/native
ALL    → @Transactional on service
```

---

## What's next (Day 24+)

Specifications / Criteria · Custom paged DTO · `@DataJpaTest` · Auditing

Ready for day review → God-level notes → Jira Done.

---

# Day 24 — Specifications (Dynamic Queries) ✅ DONE

## Day 24 Objective

Connect:

```text
Days 17–18 — static queries (derived, @Query — fixed WHERE)
Day 20 — Pageable
        ↓
Day 24 — Specification — build WHERE at runtime from optional filters
```

Core question:

> **How do I search employees when the user may send name, dept, minSalary — any combination — without 20 repository methods?**

In scope:

- WHY Specifications (optional filters, search forms)
- `JpaSpecificationExecutor<EmployeeEntity>` on repository
- `Specification<EmployeeEntity>` — lambda or static factory methods
- Combine: chain `.and(...)`, if-blocks, or `Specification.allOf(list)`
- `findAll(Specification, Pageable)` — dynamic query + pagination
- Criteria API basics (`Root`, `CriteriaBuilder`, `Predicate`) — via Specification
- Service builds spec from query params; `@Transactional(readOnly=true)`
- HTTP search endpoint: `GET /employees/search`

Out of scope (later):

- Full Criteria API without Specification wrapper
- `@DataJpaTest`, Auditing (Day 25–26)
- Elasticsearch / full-text search

Do not start experiments until Jira ticket exists.

Jira ticket: created.

---

# Day 24 Experiment 1 — WHY Specifications? ✅ DONE

- Q1: optional/null params → can't use one fixed derived method; combinatorial explosion of method names; new filter = new methods or messy if-chains. ✅
- Q2: `JpaSpecificationExecutor` adds `findAll(Specification, Pageable)` etc. — dynamic WHERE at runtime. ✅
- Q3: Specification uses entity field names (`name`, `department`), not DB columns. ✅

# Day 24 Experiment 2 — JpaSpecificationExecutor + EmployeeSpecs ✅ DONE

- Extended `EmployeeRepository` with `JpaSpecificationExecutor<EmployeeEntity>`.
- Created `EmployeeSpecs` with static factories: `nameContains`, `departmentName`, `salaryGreaterThan`.
- Runner: chained specs with `.and(...)` — all three filters combined in one query.
- Observed SQL: JOIN departments + `lower(name) like ?` + `d.name=?` + `salary>?`.
- Pitfall 1: `Specification` is **immutable** — `spec.and(x)` without reassignment/chaining does nothing.
- Pitfall 2: typo in LIKE pattern (`"%d"` instead of `"%"`) → SQL shape correct but bind value wrong → 0 rows while manual SQL returns 1.
- Pitfall 3: avoid `Specification.where(null)` — ambiguous in Spring Data 4; chain directly or use `allOf`.

# Day 24 Experiment 3 — Optional filters in service ✅ DONE

- Service builds spec only when param is present (`null` / blank skipped).
- Used `List<Specification>` + `Specification.allOf(specs)` — clean when filters are optional.
- Alternative: if-blocks with `spec = spec == null ? first : spec.and(next)`.
- Empty list → `allOf([])` = unrestricted → all employees (still paginated).

# Day 24 Experiment 4 — HTTP search endpoint ✅ DONE

- `GET /employees/search?name=&department=&minSalary=&page=&size=&sort=`
- Controller: `@RequestParam(required = false)` on each filter + `Pageable`.
- Service: `@Transactional(readOnly = true)` + `findAll(finalSpec, pageable).map(toEmployee)`.
- Verified: Hibernate SQL matches manual JOIN + dynamic WHERE per param combination.
- Route `/employees/search` does not clash with `/employees/{id}` (literal path vs int id).

---

# Day 24 — God-Level Notes (Notebook)

## Why Specifications?

Static queries fail when filters are optional:

```text
findByName
findByDepartment_Name
findByNameAndDepartment_NameAndSalaryGreaterThan
… → combinatorial explosion
```

Specifications = **build WHERE at runtime** from optional filters (search forms, admin filters, report params).

---

## Stack (three pieces)

| Piece | Role |
|-------|------|
| `JpaSpecificationExecutor<T>` on repository | Adds `findAll(Spec, Pageable)`, `count(Spec)`, etc. |
| `Specification<T>` | Lambda/factory returning `Predicate` |
| `EmployeeSpecs` (static factories) | Reusable, testable filter methods |

```java
public interface EmployeeRepository
        extends JpaRepository<EmployeeEntity, Integer>, JpaSpecificationExecutor<EmployeeEntity> { }
```

---

## Criteria API via Specification

Each spec receives `(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb)`:

| API | Example in our project |
|-----|------------------------|
| `root.get("name")` | employee name field |
| `root.get("department").get("name")` | join path to dept name |
| `cb.like(cb.lower(...), pattern)` | case-insensitive name search |
| `cb.equal(...)` | exact dept name match |
| `cb.greaterThan(...)` | salary > min |

`departmentName` spec → Hibernate generates **JOIN departments** (same as manual SQL).

---

## EmployeeSpecs pattern

```java
public static Specification<EmployeeEntity> nameContains(String name) {
    return (root, query, cb) ->
        cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%");
}

public static Specification<EmployeeEntity> departmentName(String deptName) {
    return (root, query, cb) ->
        cb.equal(root.get("department").get("name"), deptName);
}

public static Specification<EmployeeEntity> salaryGreaterThan(BigDecimal min) {
    return (root, query, cb) ->
        cb.greaterThan(root.get("salary"), min);
}
```

Field names = **entity** names, not DB columns.

---

## Combining specs (three valid styles)

**1. Chain (all filters always present — runner / fixed search):**

```java
EmployeeSpecs.nameContains("Vijendra")
    .and(EmployeeSpecs.departmentName("IT"))
    .and(EmployeeSpecs.salaryGreaterThan(new BigDecimal("100")));
```

**2. If-blocks (optional params):**

```java
Specification<EmployeeEntity> spec = null;
if (name != null && !name.isBlank()) {
    spec = spec == null ? EmployeeSpecs.nameContains(name) : spec.and(EmployeeSpecs.nameContains(name));
}
// repeat for dept, minSalary
```

**3. `Specification.allOf(list)` (optional params — what we used):**

```java
List<Specification<EmployeeEntity>> specs = new ArrayList<>();
if (name != null && !name.isBlank()) specs.add(EmployeeSpecs.nameContains(name));
if (dept != null && !dept.isBlank()) specs.add(EmployeeSpecs.departmentName(dept));
if (minSalary != null) specs.add(EmployeeSpecs.salaryGreaterThan(minSalary));
Specification<EmployeeEntity> finalSpec = Specification.allOf(specs);
```

**Immutable rule:** `spec.and(x)` returns a **new** spec — must chain or reassign.

**Avoid:** `Specification.where(null)` — compile/ambiguity issues in Spring Data 4.

---

## Repository + service + controller

```java
// repo
Page<EmployeeEntity> findAll(Specification<EmployeeEntity> spec, Pageable pageable);

// service
@Transactional(readOnly = true)
public Page<Employee> searchEmployees(String name, String dept, BigDecimal minSalary, Pageable pageable) {
    // build finalSpec from optional params
    return employeeRepository.findAll(finalSpec, pageable).map(this::toEmployee);
}

// controller
@GetMapping("/search")
public Page<EmployeeResponse> searchEmployees(
        @RequestParam(required = false) String name,
        @RequestParam(required = false) String department,
        @RequestParam(required = false) BigDecimal minSalary,
        Pageable pageable) { ... }
```

`findAll(null, pageable)` or `allOf([])` → no WHERE → all rows (paginated).

---

## vs other query styles

| Need | Use |
|------|-----|
| Fixed query, no optional filters | derived method / `@Query` |
| Optional search filters | **Specification** |
| Bulk UPDATE/DELETE | `@Modifying` (Day 23) |
| Eager fetch on paginated search | `@EntityGraph` on `findAll(Spec, Pageable)` (Day 21) |

Spec defines **filter** (WHERE). EntityGraph defines **fetch plan** (JOIN for load). Can combine both on same repo method.

---

## Observed SQL

**All three filters:**

```sql
select ee..., ee1_0.salary
from employees ee1_0
join departments d1_0 on d1_0.id = ee1_0.department_id
where lower(ee1_0.name) like ? and d1_0.name = ? and ee1_0.salary > ?
```

**One filter only** → only that predicate appears in WHERE.

**No filters** → SELECT all employees + count query for `Page<T>`.

---

## Pitfalls we hit

| Pitfall | Symptom | Fix |
|---------|---------|-----|
| `%d` typo in LIKE | Manual SQL 1 row, app 0 rows | `"%" + name + "%"` not `"%d"` |
| `spec.and(x)` without chain | Only first filter in SQL | chain or reassign |
| Wrong spec for param | dept filter searches name | use `departmentName(dept)` not `nameContains(dept)` |
| `where(null)` | won't compile / ambiguous | chain or `allOf` |

---

## Decision tree

```text
All filters always required?
├─ Yes → derived method or @Query
└─ No (optional / search API)
    ├─ JpaSpecificationExecutor on repo
    ├─ Static spec factories (EmployeeSpecs)
    ├─ Service builds spec from @RequestParam
    ├─ findAll(spec, pageable)
    ├─ @Transactional(readOnly=true)
    └─ @EntityGraph on findAll(Spec, Pageable) if mapping touches lazy associations
```

---

## Hard rules (memorize)

1. **Optional filters** → Specifications, not 20 derived method names.
2. Spec paths = **entity field names** (`department.name`), not table/column names.
3. **`Specification` is immutable** — chain `.and()` or use `allOf`.
4. **Search + pagination** → `findAll(spec, pageable)` — don't add LIMIT/OFFSET manually.
5. **`@Transactional(readOnly = true)`** on search/read service methods.
6. Match **spec to param** — name → `nameContains`, dept → `departmentName`, salary → `salaryGreaterThan`.
7. Empty spec / empty `allOf` → unrestricted query (all rows, paginated).

---

## What we proved

| Test | Result |
|------|--------|
| Chained 3 specs in runner | 1 row: Vijendra, IT, salary > 100 |
| `GET /search?name=Vijendra&department=IT&minSalary=100` | Same SQL as manual query |
| Single param | Only that WHERE clause |
| No params | Paginated full list |
| LIKE typo fix | App result matches SQL client |

---

## What's next (Day 25+)

| Topic | Why |
|-------|-----|
| JPA Auditing (`@CreatedDate`, `@LastModifiedDate`) | auto timestamps on persist/update |
| `@EnableJpaAuditing` | turn on auditing |
| `@DataJpaTest` (Day 26) | slice test for repositories |

Day 24 = dynamic WHERE at runtime without combinatorial repository methods.

Ready for day review → God-level notes → Jira Done.

---

# Day 25 — JPA Auditing (`@CreatedDate` / `@LastModifiedDate`) ✅ DONE

## Day 25 Objective

Connect:

```text
Days 1–24 — entities, repos, save/update, search
        ↓
Day 25 — JPA Auditing — auto-set createdAt / updatedAt on persist & update
```

Core question:

> **Who sets `createdAt` and `updatedAt` — the developer in every service method, or the persistence layer automatically?**

In scope:

- WHY auditing (consistency, no forgotten timestamps, audit trail basics)
- `@EntityListeners(AuditingEntityListener.class)` on entity
- `@CreatedDate`, `@LastModifiedDate` on fields
- `@EnableJpaAuditing` on config / main application class
- `AuditorAware<String>` — optional `createdBy` / `lastModifiedBy` (who did it)
- `LocalDateTime` vs `Instant` for audit columns
- DB migration: add `created_at`, `updated_at` columns to `employees`
- Observe: INSERT sets both; UPDATE changes only `updatedAt`
- `@Transactional` on save/update (auditing runs inside persistence lifecycle)

Out of scope (later):

- `@DataJpaTest` slice tests (Day 26)
- Envers / full revision history
- Custom `@PrePersist` / `@PreUpdate` (contrast only — prefer auditing for timestamps)

Do not start experiments until Jira ticket exists.

Jira ticket: created.

---

# Day 25 Experiment 1 — WHY JPA Auditing? ✅ DONE

- Q1: manual set in every service → human error, inconsistent across team/services, easy to forget in prod. ✅
- Q2: entity fields + DB columns needed — but also `@EntityListeners(AuditingEntityListener.class)` on entity and `@EnableJpaAuditing` at app/config level (not just annotations on fields). ✅
- Q3: UPDATE → only `updatedAt` changes; `createdAt` stays frozen from INSERT. ✅

# Day 25 Experiment 2 — DB columns + entity annotations ✅ DONE

- Added `created_at`, `updated_at` to `employees` (MySQL).
- `EmployeeEntity`: `LocalDateTime createdAt`, `updatedAt` + `@CreatedDate`, `@LastModifiedDate`.
- `@EntityListeners(AuditingEntityListener.class)` on entity class.
- Before `@EnableJpaAuditing`: INSERT failed — `created_at` null → `SQLIntegrityConstraintViolationException` (expected; listener/auditing not fully active yet, or columns NOT NULL with no value set).

# Day 25 Experiment 3 — @EnableJpaAuditing + prove INSERT/UPDATE ✅ DONE

- Added `@EnableJpaAuditing` on `SpringBoot30DayApplication`.
- INSERT: both `created_at` and `updated_at` set to the **same** value. ✅
- UPDATE (runner): only `updated_at` changed; `created_at` unchanged. ✅

# Day 25 Experiment 4 — AuditorAware (optional) ⏭️ SKIPPED

- Skipped for now — concept understood (`@CreatedBy`, `@LastModifiedBy` + `AuditorAware<T>` bean).
- Revisit when project needs "who changed this row" (usually with Spring Security username).

---

# Day 25 — God-Level Notes (Notebook)

## Why JPA Auditing?

Every create/update needs timestamps. Manual `setCreatedAt(now())` in every service:

- easy to forget
- inconsistent across team / code paths
- duplicates logic in JDBC, JPA, batch jobs

**Auditing** = Spring sets audit fields automatically at **persist/update** time via entity listener — one place, all entities.

---

## Three pieces (all required for timestamps)

| Piece | Where | Role |
|-------|-------|------|
| `@CreatedDate` / `@LastModifiedDate` | Entity fields | Mark which fields are audit metadata |
| `@EntityListeners(AuditingEntityListener.class)` | Entity class | Listener that **sets** those fields on lifecycle events |
| `@EnableJpaAuditing` | Main class or `@Configuration` | **Turns on** auditing machinery at startup |

Annotations on fields alone = labels only. Without listener + `@EnableJpaAuditing` → fields stay `null` → NOT NULL column → `SQLIntegrityConstraintViolationException`.

---

## How it works (lifecycle)

```text
INSERT (@PrePersist)
  → AuditingEntityListener sets @CreatedDate AND @LastModifiedDate (same time)

UPDATE (@PreUpdate)
  → AuditingEntityListener sets @LastModifiedDate only
  → @CreatedDate frozen forever
```

Runs inside JPA persistence lifecycle — no extra code in service (works with `save()` / `saveAndFlush()`).

---

## Entity setup (what we built)

```java
@Entity
@EntityListeners(AuditingEntityListener.class)
public class EmployeeEntity {

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}
```

**App:**

```java
@EnableJpaAuditing
@SpringBootApplication
public class SpringBoot30DayApplication { ... }
```

**DB:**

```sql
ALTER TABLE employees
  ADD COLUMN created_at DATETIME(6),
  ADD COLUMN updated_at DATETIME(6);
```

Use `DATETIME(6)` for `LocalDateTime` microsecond precision with Hibernate + MySQL.

---

## What we proved

| Event | `created_at` | `updated_at` |
|-------|--------------|--------------|
| INSERT | set | set (same value) |
| UPDATE | unchanged | new value |

Observed via runner (`updateEmployeeName`) + HeidiSQL.

---

## `@EntityListeners(AuditingEntityListener.class)` — mandatory?

**Yes** for Spring Data JPA auditing on that entity (or on a `@MappedSuperclass` base entity shared by many tables).

| Without | Result |
|---------|--------|
| `@EntityListeners` | Fields never auto-populated |
| `@EnableJpaAuditing` | Listener not wired — same failure |
| DB NOT NULL + no value | INSERT fails |

**Alternative:** custom `@PrePersist` / `@PreUpdate` on entity — works but you maintain it per entity; prefer auditing for standard timestamps.

---

## Optional: who changed it (`AuditorAware`) — skipped, for later

| Annotation | When set |
|------------|----------|
| `@CreatedBy` | INSERT |
| `@LastModifiedBy` | INSERT + every UPDATE |

Requires `AuditorAware<T>` bean — e.g. `() -> Optional.of(SecurityContext username)` or `"system"` in dev.

Use when audit trail needs **who**, not just **when**.

---

## vs manual / other approaches

| Approach | Pros | Cons |
|----------|------|------|
| Manual in service | Simple for 1 place | Forgotten, duplicated, inconsistent |
| `@PrePersist` on entity | No Spring Data dep | Per-entity boilerplate |
| **JPA Auditing** | Central, reusable, `@CreatedBy` ready | Needs 3-piece setup + DB columns |
| Hibernate Envers | Full revision history | Heavy — different use case |

---

## Hard rules (memorize)

1. **Timestamps** → `@CreatedDate` + `@LastModifiedDate` + `@EntityListeners(AuditingEntityListener.class)` + `@EnableJpaAuditing`.
2. **INSERT** sets both dates; **UPDATE** changes only `@LastModifiedDate`.
3. **Don't** manually set audit fields in service unless you have a special reason.
4. **DB columns must exist** (or DDL auto-create) — auditing fills Java fields before flush, not magic after INSERT.
5. **`LocalDateTime`** common with MySQL `DATETIME(6)`; **`Instant`** if you want UTC everywhere.
6. **`@Modifying` bulk UPDATE** bypasses entity lifecycle — audit fields **not** auto-updated on those rows (load + save or trigger in SQL).

---

## Decision tree

```text
Need createdAt / updatedAt on entity?
├─ Yes
│   ├─ Add DB columns
│   ├─ @CreatedDate / @LastModifiedDate on entity
│   ├─ @EntityListeners(AuditingEntityListener.class)
│   ├─ @EnableJpaAuditing on app/config
│   └─ Verify INSERT (both set) + UPDATE (only updatedAt)
└─ Need who changed it too?
    └─ @CreatedBy / @LastModifiedBy + AuditorAware bean (Day 25+ / with Security)
```

---

## What's next (Day 26+)

| Topic | Why |
|-------|-----|
| `@DataJpaTest` | slice test — repo + JPA only, no full `@SpringBootTest` |
| `@CreatedBy` + Security | when auth exists in project |
| Envers | full entity revision history (separate topic) |

Day 25 = automatic **when** on persist/update — persistence layer, not scattered service code.

Ready for day review → God-level notes → Jira Done.

---

# Day 26 — `@DataJpaTest` (Repository Slice Tests) ✅ DONE

## Day 26 Objective

Connect:

```text
Days 17–25 — repositories, queries, specs, auditing
        ↓
Day 26 — @DataJpaTest — test repository layer in isolation (fast, focused)
```

Core question:

> **How do I test `EmployeeRepository` without starting the full Spring Boot app, Tomcat, controllers, and every other bean?**

In scope:

- WHY slice tests vs `@SpringBootTest` (speed, focus, less flakiness)
- Add `spring-boot-starter-data-jpa-test` + H2 (`scope=test`)
- `@DataJpaTest` — loads only JPA + repo + in-memory DB (H2 for tests)
- `@Autowired EmployeeRepository` + `EntityManager` / `TestEntityManager` for setup
- Test: save + findById, derived query (`findByEmail`), custom `@Query` JPQL
- `@ActiveProfiles("test")` + `application-test.properties` for H2
- `@Transactional` on test class — rolls back after each test (default)
- AAA pattern: Arrange → Act → Assert

Out of scope (later):

- `@WebMvcTest` (controller slice)
- `@MockBean` / Mockito service tests
- Testcontainers + real MySQL in CI
- Integration tests across full stack

Do not start experiments until Jira ticket exists.

Jira ticket: created.

**Learner note:** zero prior testing experience — Day 26 starts from basics; Mockito not required for `@DataJpaTest` repo tests.

---

# Day 26 Experiment 1 — WHY @DataJpaTest? ✅ DONE

- Q1: `@SpringBootTest` boots entire app (all modules) → slow, wasteful when only testing repo/DB layer. ✅
- Q2: **Not loaded:** Tomcat, controllers, services. **Loaded:** JPA config, repositories, test DB (H2). ✅
- Q3: cleaned up automatically — `@DataJpaTest` wraps each test in `@Transactional` + **rolls back** after test; data doesn't leak to next test. Can disable with `@Commit` / `@Rollback(false)` if needed. ✅

# Day 26 Experiment 2 — test dependency + H2 config ✅ DONE

- Added `spring-boot-starter-data-jpa-test` + `h2` (`scope=test`) to `pom.xml`.
- `spring-boot-starter-data-jpa-test` transitively includes `spring-boot-starter-test` (JUnit, assertions) — no need for both explicitly.
- Created `application-test.properties` (H2 in-memory, `ddl-auto=create-drop`, H2Dialect).
- `mvn test` → BUILD SUCCESS (0 tests yet — expected).
- Note: file in `src/main/resources/application-test.properties` works with `@ActiveProfiles("test")`; common alternative is `src/test/resources/`.
- **Spring Boot 4 gotcha:** `spring-boot-starter-test` alone does NOT include `@DataJpaTest` — add `spring-boot-starter-data-jpa-test` and use import `org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest` (not `...test.autoconfigure.orm.jpa...`).

# Day 26 Experiment 3 — first @DataJpaTest (save + findById) ✅ DONE

- `EmployeeRepositoryTest` with `@DataJpaTest` + `@ActiveProfiles("test")`.
- Test: save employee → `findById` → assert name + email. ✅
- `mvn test` → Tests run: 1, BUILD SUCCESS.
- Observed: slice context logs (Hibernate, H2, repo scan) — no Tomcat/controllers (expected).

# Day 26 Experiment 4 — derived query test (`findByEmail`) ✅ DONE

- Added `shouldFindEmployeeByEmail` — save → `findByEmail` → assert name + email. ✅
- Proves Spring Data **derived query** works without `@Query`.

# Day 26 Experiment 5 — custom `@Query` test (JPQL) ✅ DONE

- Setup: `DepartmentEntity` + 2 employees via `EntityManager.persist()` + `flush()`.
- Test: `findEmployeesByDepartmentNameAndSalaryAbove("IT", 100)` → 1 row (high earner only). ✅
- `mvn test` → Tests run: 3, BUILD SUCCESS.

---

# Day 26 — God-Level Notes (Notebook)

## Why `@DataJpaTest`?

Repository logic (save, derived queries, `@Query`) must work against a real DB. Starting the full app for every test is:

- **slow** (Tomcat, all beans)
- **brittle** (unrelated beans can break repo tests)
- **overkill** (controllers/services not under test)

**`@DataJpaTest`** = slice test — only JPA + repositories + embedded H2.

---

## `@SpringBootTest` vs `@DataJpaTest`

| | `@SpringBootTest` | `@DataJpaTest` |
|---|-------------------|----------------|
| Loads | Full application | JPA slice only |
| Tomcat | Yes | No |
| Controllers / services | Yes | No |
| Repositories + JPA | Yes | Yes |
| DB | Configurable (often Testcontainers) | Embedded H2 by default |
| Speed | Slow | Fast |
| Use for | End-to-end integration | Repository / query tests |

---

## Spring Boot 4 — test dependencies (memorize)

| Boot 3 | Boot 4 |
|--------|--------|
| `spring-boot-starter-test` includes `@DataJpaTest` | JPA test slice = **separate** starter |
| import `...test.autoconfigure.orm.jpa.DataJpaTest` | import `org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest` |

**Minimal for repo tests:**

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa-test</artifactId>
    <scope>test</scope>
</dependency>
<dependency>
    <groupId>com.h2database</groupId>
    <artifactId>h2</artifactId>
    <scope>test</scope>
</dependency>
```

`spring-boot-starter-data-jpa-test` **transitively includes** `spring-boot-starter-test`.

---

## Test class skeleton

```java
@DataJpaTest
@ActiveProfiles("test")
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EntityManager entityManager; // or TestEntityManager

    @Test
    void shouldSaveAndFindEmployeeById() { ... }
}
```

| Annotation | Role |
|------------|------|
| `@DataJpaTest` | JPA slice context |
| `@ActiveProfiles("test")` | Load `application-test.properties` (H2) |
| `@Autowired EmployeeRepository` | Real repo bean under test |
| `@Test` | JUnit — marks test method |
| `@Autowired EntityManager` | Direct persist for setup (dept, etc.) |

---

## AAA pattern (every test)

```text
Arrange  →  create + persist test data
Act      →  call repository method
Assert   →  assertEquals / assertTrue / assertFalse
```

Example assertions:

```java
assertTrue(found.isPresent());
assertEquals("expected", found.get().getName());
assertEquals(1, result.size());
```

---

## What we tested (3 tests)

| Test | What it proves |
|------|----------------|
| `shouldSaveAndFindEmployeeById` | `save()` + `findById()` basic CRUD |
| `shouldFindEmployeeByEmail` | derived query `findByEmail` |
| `shouldFindEmployeesByDepartmentAndMinSalary_usingJpqlQuery` | custom `@Query` JPQL + JOIN + filter |

---

## Setup with `EntityManager` / `TestEntityManager`

When test data needs entities without a dedicated repo (e.g. `DepartmentEntity`):

```java
DepartmentEntity dept = new DepartmentEntity();
dept.setName("IT");
entityManager.persist(dept);

EmployeeEntity emp = new EmployeeEntity();
emp.setDepartment(dept);
entityManager.persist(emp);

entityManager.flush(); // ensure visible before repository query
```

`TestEntityManager` is a Spring Boot test wrapper around `EntityManager` — either works in `@DataJpaTest`.

---

## `mvn test` lifecycle

```text
compile main → test-compile → run @Test methods → report pass/fail
```

- Does **not** start Tomcat or run the app on 8080
- Slice context still logs Hibernate/H2 startup — **expected** (not zero logs)
- Result line: `Tests run: 3, Failures: 0, BUILD SUCCESS`

---

## Test DB vs production DB

| | Production (`application.properties`) | Tests (`application-test.properties`) |
|---|--------------------------------------|---------------------------------------|
| DB | MySQL `spring_boot_30_days` | H2 in-memory |
| Needs HeidiSQL running | Yes | **No** |
| Data | Real / persistent | Created per test run, rolled back per test |

`@DataJpaTest` may replace DataSource with embedded H2 even when profile is active — tests stay isolated from MySQL.

---

## Rollback per test (default)

`@DataJpaTest` is `@Transactional` → each test rolls back at end → tests don't pollute each other.

To keep data (rare): `@Commit` or `@Rollback(false)` on that test method.

---

## Hard rules (memorize)

1. **Repo tests** → `@DataJpaTest`, not `@SpringBootTest`.
2. **Boot 4:** `spring-boot-starter-data-jpa-test` + new import path for `@DataJpaTest`.
3. **`@ActiveProfiles("test")`** → H2 config, not MySQL.
4. **AAA** in every test — Arrange, Act, Assert.
5. **No Mockito** needed for repo slice tests — real repo + real H2.
6. **`entityManager.persist` + `flush`** when setup needs entities beyond autowired repos.
7. **`mvn test`** = run tests; **`mvn spring-boot:run`** = start app — different commands.

---

## Decision tree

```text
What are you testing?
├─ Repository / @Query / derived method → @DataJpaTest + H2
├─ Controller / HTTP / JSON → @WebMvcTest (later)
├─ Service with mocked deps → @ExtendWith(MockitoExtension) / @MockBean (later)
└─ Full flow (API → DB → response) → @SpringBootTest (later, slow)
```

---

## What's next (Day 27+)

| Topic | Why |
|-------|-----|
| `@WebMvcTest` | controller slice — MockMvc |
| Mockito / `@MockBean` | service unit tests |
| Testcontainers | real MySQL in CI |
| `@SpringBootTest` | full integration when needed |

Day 26 = first automated tests — repository layer, fast, no mocks, no full app.

Ready for day review → God-level notes → Jira Done.

---

# Day 27 — `@WebMvcTest` (Controller Slice + MockMvc) ✅ DONE

## Day 27 Objective

Connect:

```text
Day 26 — @DataJpaTest (repository layer, real DB, no mocks)
        ↓
Day 27 — @WebMvcTest — test HTTP layer (controller) in isolation with mocked service
```

Core question:

> **How do I test `GET /employees/{id}` returns correct JSON/status without starting the full app or hitting a real database?**

In scope:

- WHY `@WebMvcTest` vs `@DataJpaTest` vs `@SpringBootTest`
- Add `spring-boot-starter-webmvc-test` (Spring Boot 4)
- `@WebMvcTest(EmployeeController.class)` — controller slice only
- `@MockitoBean` EmployeeService — controller's dependency faked
- `MockMvc` — simulate HTTP requests (`get`, `status`, `jsonPath`)
- Test: `GET /employees/{id}` → 200 + JSON body
- Boot 4 import: `org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest`

Out of scope (done in notes only, not implemented):

- POST validation → 400 test (`mockMvc.perform(post(...))`)
- `@Import(GlobalExceptionHandler.class)` for exception handler in slice
- `@SpringBootTest` + real DB end-to-end
- Testcontainers / Security tests

Do not start experiments until Jira ticket exists.

Jira ticket: created.

---

# Day 27 Experiment 1 — WHY @WebMvcTest? ✅ DONE

- Q1: API test ≠ full stack controller→repo in slice test. `@WebMvcTest` tests **controller/web layer only**; service is **mocked** — that's the point of a slice. Full stack = `@SpringBootTest` (later). ✅
- Q2: **Don't use real `EmployeeService`** in `@WebMvcTest` — use `@MockitoBean` to fake it. Real service pulls in repo, DAO, DB → integration test, not controller slice. ✅
- Q3: `MockMvc` simulates HTTP requests against the controller (in-process) — status, headers, JSON — without starting full app on 8080. ✅

# Day 27 Experiment 2 — webmvc-test dependency + skeleton ✅ DONE

- Added `spring-boot-starter-webmvc-test` to `pom.xml`.
- Boot 4: `@MockitoBean` (not `@MockBean`) — `org.springframework.test.context.bean.override.mockito.MockitoBean`.
- **Gotcha:** `@EnableJpaAuditing` on `SpringBoot30DayApplication` breaks `@WebMvcTest` → `JPA metamodel must not be empty`. Fix: move `@EnableJpaAuditing` to separate `JpaAuditingConfig`, remove from main class. **Not** fixed by `@ActiveProfiles("test")`.
- Commented out `@EnableJpaAuditing` on main → controller test passes. **Prod fix:** add `JpaAuditingConfig` so Day 25 auditing still works when app runs.

# Day 27 Experiment 3 — GET /employees/{id} + MockMvc + MockitoBean ✅ DONE

- `EmployeeControllerTest`: `@WebMvcTest` + `@MockitoBean EmployeeService` + `MockMvc`.
- `when(service.getEmployee(1)).thenReturn(employee)` → mock, no DB.
- `mockMvc.perform(get("/employees/1"))` → 200 + `jsonPath` assertions. ✅
- `mvn test` → Tests run: 4 (3 repo + 1 controller), BUILD SUCCESS.

# Day 27 Experiment 4 — jsonPath assertions ✅ DONE

- Covered in Exp 3: `jsonPath("$.id")`, `jsonPath("$.name")`, `jsonPath("$.email")`.
- `status().isOk()` asserts HTTP 200.

# Day 27 Experiment 5 — POST validation → 400 ⏭️ SKIPPED

- Skipped for now — pattern documented in God-level notes below.
- When needed: `mockMvc.perform(post("/employees").contentType(JSON).content(...))` + `@Import(GlobalExceptionHandler.class)`.

---

# Day 27 — God-Level Notes (Notebook)

## Why `@WebMvcTest`?

Day 26 tested **repository** (does SQL work?).  
Day 27 tests **controller** (does HTTP mapping + JSON work?).

```text
@DataJpaTest     → repo + real H2        → no HTTP, no mocks
@WebMvcTest      → controller + MockMvc  → mock service, no DB
@SpringBootTest  → full stack            → slow, later
```

Controller test question: *"Given service returns X, does my endpoint return correct status + JSON?"*

---

## Three test types (memorize)

| Annotation | Loads | DB | Service | Use for |
|------------|-------|-----|---------|---------|
| `@DataJpaTest` | JPA + repos | H2 (real) | No | Repository queries |
| `@WebMvcTest` | Web + 1 controller | No | **Mocked** | HTTP / JSON / status |
| `@SpringBootTest` | Everything | Yes (configurable) | Real | Full integration |

---

## Spring Boot 4 — web test dependencies

| Boot 3 | Boot 4 |
|--------|--------|
| `@WebMvcTest` in `spring-boot-starter-test` | **`spring-boot-starter-webmvc-test`** |
| import `...web.servlet.WebMvcTest` | import `org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest` |
| `@MockBean` | **`@MockitoBean`** — `org.springframework.test.context.bean.override.mockito.MockitoBean` |

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-webmvc-test</artifactId>
    <scope>test</scope>
</dependency>
```

Transitive includes `spring-boot-starter-test` (JUnit, Mockito).

---

## Test class skeleton

```java
@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private EmployeeService employeeService;

    @Test
    void shouldReturnEmployeeById() throws Exception {
        Employee employee = new Employee("Krishna H", "krishna@gmail.com");
        employee.setId(1);
        employee.setSalary(new BigDecimal("50000"));

        when(employeeService.getEmployee(1)).thenReturn(employee);

        mockMvc.perform(get("/employees/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Krishna H"))
                .andExpect(jsonPath("$.email").value("krishna@gmail.com"));
    }
}
```

---

## Key pieces explained

| Piece | Role |
|-------|------|
| `@WebMvcTest(EmployeeController.class)` | Load **only** this controller + web infra |
| `@MockitoBean EmployeeService` | Fake service — **first Mockito use** |
| `when(...).thenReturn(...)` | Define mock behaviour (static import from `Mockito`) |
| `MockMvc` | Simulate HTTP in-process (no browser, no port 8080) |
| `get("/employees/1")` | HTTP GET request |
| `status().isOk()` | Assert HTTP 200 |
| `jsonPath("$.name")` | Assert JSON field (Jayway JsonPath syntax) |

---

## Static imports (why?)

```java
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
```

**Normal import** = class (`EmployeeService`, `MockMvc`)  
**Static import** = call method directly (`when`, `get`, `status`) without class prefix

Beans (`@MockitoBean`) ≠ static methods (`when`, `get`).

---

## Flow diagram

```text
mockMvc.perform(GET /employees/1)
    ↓
real EmployeeController.getEmployee(1)
    ↓
@MockitoBean EmployeeService.getEmployee(1)  → returns fake Employee (you defined)
    ↓
controller maps to EmployeeResponse JSON
    ↓
MockMvc captures response → assert status + jsonPath
```

No MySQL. No H2. No repository.

---

## `@ActiveProfiles("test")` — needed?

| Test type | Need `test` profile? |
|-----------|---------------------|
| `@DataJpaTest` | **Yes** — switches to H2 |
| `@WebMvcTest` | **No** — no database involved |

---

## Gotcha: `@EnableJpaAuditing` on main class

**Error:** `JPA metamodel must not be empty` / `jpaAuditingHandler` bean creation fails.

**Cause:** `@WebMvcTest` loads `SpringBoot30DayApplication` as `@SpringBootConfiguration`. `@EnableJpaAuditing` on same class tries to boot JPA auditing without full JPA slice.

**Fix:** Move auditing off main class:

```java
@Configuration
@EnableJpaAuditing
public class JpaAuditingConfig { }
```

Remove `@EnableJpaAuditing` from `SpringBoot30DayApplication`.

Spring docs: avoid area-specific config (JPA, security) on main class when using test slices.

---

## POST test pattern (skipped — for later)

When you need to test validation / create endpoint:

```java
@WebMvcTest(EmployeeController.class)
@Import(GlobalExceptionHandler.class)  // needed — @RestControllerAdvice not auto-loaded in slice
class EmployeeControllerTest {

    @Test
    void shouldReturn400WhenCreateInvalid() throws Exception {
        mockMvc.perform(post("/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {"name":"","email":"bad","salary":-1}
                    """))
            .andExpect(status().isBadRequest());
    }
}
```

Imports: `post` from `MockMvcRequestBuilders`, `MediaType.APPLICATION_JSON`.

`GlobalExceptionHandler` is **not** loaded by `@WebMvcTest` by default — add `@Import` or test returns 500 instead of 400.

---

## What we proved

| Test | Result |
|------|--------|
| `GET /employees/1` with mocked service | 200 + correct JSON fields |
| `mvn test` | 4 tests pass (3 repo + 1 controller) |
| No Tomcat on 8080 | Slice context only |

---

## Hard rules (memorize)

1. **Controller tests** → `@WebMvcTest`, not `@DataJpaTest` or `@SpringBootTest`.
2. **Mock the service** with `@MockitoBean` — never real `EmployeeService` in slice.
3. **Boot 4:** `spring-boot-starter-webmvc-test` + new `@WebMvcTest` import + `@MockitoBean`.
4. **`when(...).thenReturn(...)`** configures mock before HTTP call.
5. **`jsonPath("$.field")`** asserts JSON response body.
6. **Don't put `@EnableJpaAuditing` on main class** — use `JpaAuditingConfig` for slice compatibility.
7. **`@ActiveProfiles("test")`** for repo tests only — not WebMvcTest.

---

## Decision tree (all test types so far)

```text
What are you testing?
├─ Repository / @Query / save-find     → @DataJpaTest + H2 + @ActiveProfiles("test")
├─ Controller / HTTP / JSON / status  → @WebMvcTest + @MockitoBean + MockMvc
├─ Service logic in isolation         → plain Mockito / @ExtendWith (later)
└─ Full API → DB → response           → @SpringBootTest (later)
```

---

## What's next (Day 28+)

| Topic | Why |
|-------|-----|
| `@SpringBootTest` | full integration when slices aren't enough |
| Service unit tests | Mockito without Spring context |
| Testcontainers | real MySQL in CI |
| `@WithMockUser` | security on controller tests |

Day 27 = test HTTP layer fast — mock service, assert status + JSON.

Ready for day review → God-level notes → Jira Done.

---

# Day 28 — Testing Complete (`@SpringBootTest` + Service Unit Tests) ⏸ ON HOLD

Jira: To Do (paused). Resume after Spring Security.

---

# Day 29 — Spring Security Core (AuthN / AuthZ / Protect APIs) ✅ DONE

## Day 29 Objective

Connect:

```text
Days 10–13 — REST APIs anyone can call (no identity)
Days 25–27 — auditing, tests
        ↓
Day 29 — Spring Security — WHO is calling, and ARE THEY ALLOWED?
```

Core question:

> **Without Security, every endpoint is public. How do I prove identity (authentication) and enforce permissions (authorization) on my Employee APIs — in a way that matches real apps and interviews?**

In scope (ONE dense day — what you need at this level):

- WHY Security exists (big picture — not annotation soup)
- Authentication vs Authorization
- Filter chain mental model
- `spring-boot-starter-security` + default lock
- `SecurityFilterChain` bean (modern API)
- `UserDetailsService` + `PasswordEncoder` (BCrypt)
- URL rules: public GET / USER write / ADMIN delete
- HTTP Basic for APIs
- `@PreAuthorize` + `@EnableMethodSecurity`
- Interview hard rules + decision tree

Out of scope (later when project needs them):

- Full OAuth2 / OIDC / social login
- JWT deep dive / refresh tokens (awareness only below)
- CSRF deep dive for SPA
- LDAP, SAML, multi-tenant IAM
- `@WithMockUser` security tests

Jira ticket: created.

---

# Day 29 Experiment 1 — WHY Security? AuthN vs AuthZ + filter chain ✅ DONE

- Q1: without Security anyone can wipe/insert/update data — production disaster. ✅
- Q2: login succeeded → AuthN OK; 403 on delete → **Authorization** failed (identity known, permission missing). ✅
- Q3: Security runs **before** controller (filter chain). If after → damage already done; gate must be at the door. ✅

# Day 29 Experiment 2 — add starter, prove default lock ✅ DONE

- Added `spring-boot-starter-security`.
- Default: all endpoints locked; console shows generated password for user `user`.
- Without auth → **401**; Basic Auth `user` + generated password → **200**. ✅
- Proved: Security runs before controller without changing EmployeeController.

# Day 29 Experiment 3 — SecurityFilterChain + users/roles + BCrypt ✅ DONE

- Created `SecurityConfig`: `BCryptPasswordEncoder`, in-memory `vijendra` (USER) + `admin` (USER+ADMIN), `SecurityFilterChain` with HTTP Basic + CSRF disabled.
- Auth with our users → 200; wrong/no auth → 401. ✅
- Default generated password gone — we own AuthN now.

# Day 29 Experiment 4 — protect Employee APIs by URL + role ✅ DONE

- URL rules: GET public; POST/PATCH USER|ADMIN; DELETE ADMIN only.
- Verified: GET no auth → 200; POST no auth → 401; USER delete → 403; ADMIN delete → allowed. ✅
- Order of `requestMatchers` matters (first match wins).

# Day 29 Experiment 5 — @PreAuthorize (method security) ✅ DONE

- `@EnableMethodSecurity` on `SecurityConfig`.
- `@PreAuthorize("hasRole('ADMIN')")` on `EmployeeService.deleteEmployee`. ✅
- URL AuthZ + method AuthZ = defense in depth.

---

# Day 29 — God-Level Notes (Notebook)

## Why Spring Security?

Without it:

```text
Anyone → GET / POST / DELETE /employees → your DB
```

Production needs two answers on every sensitive request:

| Question | Name |
|----------|------|
| Who are you? | **Authentication (AuthN)** |
| Are you allowed to do this? | **Authorization (AuthZ)** |

Interviews and real apps both assume this. Controllers stay the same; Security is the **gate before** them.

---

## AuthN vs AuthZ (never confuse)

| | Authentication | Authorization |
|---|----------------|---------------|
| Meaning | Prove identity | Check permission |
| Fail status | **401** Unauthorized | **403** Forbidden |
| Example | Wrong password | USER tries DELETE (ADMIN only) |

Login OK + delete denied = AuthN succeeded, AuthZ failed.

---

## Filter chain (big picture)

```text
HTTP request
    ↓
Security Filter Chain   ← BEFORE @RestController
    ↓
AuthN (who?)
    ↓
AuthZ (allowed for this URL / method?)
    ↓
Your controller / service
```

If Security ran **after** the controller, data could already be changed. Gate must be at the door.

---

## Three beans you must understand

| Bean | Job |
|------|-----|
| `PasswordEncoder` | Hash passwords — **BCrypt** (salted, one-way) |
| `UserDetailsService` | Load user + roles by username |
| `SecurityFilterChain` | Auth mechanism (Basic) + URL AuthZ rules |

Modern config = `@Bean SecurityFilterChain` — **not** deprecated `WebSecurityConfigurerAdapter`.

---

## What we built (`SecurityConfig`)

```text
spring-boot-starter-security
        ↓
PasswordEncoder = BCryptPasswordEncoder
UserDetailsService = InMemoryUserDetailsManager
  - vijendra / … → ROLE_USER
  - admin / …    → ROLE_USER + ROLE_ADMIN
        ↓
SecurityFilterChain
  - csrf.disable()          (API + Basic Auth learning)
  - httpBasic()
  - GET /employees/**       → permitAll
  - DELETE /employees/**    → hasRole("ADMIN")
  - POST/PATCH              → hasAnyRole("USER","ADMIN")
  - anyRequest              → authenticated
        ↓
@EnableMethodSecurity
@PreAuthorize("hasRole('ADMIN')") on deleteEmployee
```

---

## Roles — critical interview detail

```java
.roles("USER")           // you write
// Spring stores as ROLE_USER

.hasRole("USER")         // you check — NO "ROLE_" prefix here
.hasRole("ROLE_USER")    // ❌ wrong — double prefix
```

`hasAnyRole("USER", "ADMIN")` = either role is enough.

---

## 401 vs 403 (memorize)

| Status | Meaning |
|--------|---------|
| **401** | Not authenticated (no/invalid credentials) |
| **403** | Authenticated, but role/permission missing |

---

## CSRF (awareness only)

- CSRF protects **browser session + cookie** form posts (attacker site tricks logged-in browser).
- Pure REST + **HTTP Basic / JWT** (no cookie session) → often `csrf.disable()`.
- Don’t disable CSRF blindly on form-login browser apps.

---

## HTTP Basic (what we used)

- Username + password sent on each request (Postman Basic Auth).
- Simple for API learning and interviews.
- Production APIs often move to **JWT / OAuth2** — same AuthN/AuthZ ideas, different token.

---

## JWT / OAuth2 (awareness — not implemented)

| Topic | One-liner |
|-------|-----------|
| **JWT** | Stateless token after login; send `Authorization: Bearer …` — no server session |
| **OAuth2 / OIDC** | Delegate login to Google/GitHub/company IdP |
| When to learn deep | When project needs mobile/SPA auth or SSO |

Same questions: who are you? what can you do? Filter chain still applies.

---

## URL security vs method security

| Layer | Where | Example |
|-------|-------|---------|
| URL | `SecurityFilterChain` | `DELETE /employees/**` → ADMIN |
| Method | `@PreAuthorize` on service | `deleteEmployee` → ADMIN |

Use URL for HTTP shape; method for business operations (and defense if URL misconfigured).

`@EnableMethodSecurity` required for `@PreAuthorize` to work.

---

## Default Boot behavior (Exp 2)

Add starter only → every endpoint locked + generated password in logs for user `user`.  
Proves Security sits in front without touching controllers. Then replace with your `SecurityConfig`.

---

## In-memory vs production users

| Now (learning) | Later (prod) |
|----------------|--------------|
| `InMemoryUserDetailsManager` | DB table + custom `UserDetailsService` |
| Hardcoded users | Register / admin-managed users |
| Same BCrypt idea | Same — always hash passwords |

---

## Decision tree

```text
Need to protect APIs?
├─ Add spring-boot-starter-security
├─ PasswordEncoder (BCrypt) + UserDetailsService
├─ SecurityFilterChain
│   ├─ httpBasic or formLogin or JWT filter
│   ├─ permitAll for public URLs
│   ├─ hasRole / hasAnyRole for sensitive URLs
│   └─ anyRequest().authenticated()
├─ Optional: @EnableMethodSecurity + @PreAuthorize
└─ Remember: 401 = AuthN, 403 = AuthZ
```

---

## Hard rules (memorize)

1. **AuthN** = who; **AuthZ** = permission. **401** vs **403**.
2. Security filter chain runs **before** controllers.
3. Never store plain-text passwords — **BCrypt**.
4. `.roles("ADMIN")` → check with `hasRole("ADMIN")` (no double `ROLE_`).
5. `requestMatchers` **order matters** — first match wins.
6. Modern config = `SecurityFilterChain` bean, not `WebSecurityConfigurerAdapter`.
7. `@PreAuthorize` needs `@EnableMethodSecurity`.
8. CSRF: understand why; disable for API+Basic/JWT learning, not blindly for form apps.

---

## What we proved

| Test | Result |
|------|--------|
| No starter → open APIs | Anyone can delete |
| Starter only | 401 without auth |
| Our users + BCrypt | Login works; generated password gone |
| GET public | 200 without auth |
| POST as anonymous | 401 |
| DELETE as USER | 403 |
| DELETE as ADMIN | allowed |
| `@PreAuthorize` on delete | Method-level ADMIN gate |

---

## What's next

| Topic | Status |
|-------|--------|
| Day 28 `@SpringBootTest` + service unit tests | ⏸ ON HOLD |
| Day 30 Flyway / Profiles / Actuator | Optional prod readiness |
| JWT deep dive | When a project needs it |
| DB-backed `UserDetailsService` | When you add a `users` table |

Day 29 = Security core locked — AuthN, AuthZ, filter chain, roles, URL + method protection. Enough for interviews and protecting APIs; extend (JWT/OAuth) when required.

Ready for day review → God-level notes → Jira Done.

---

# Day 30 — Flyway + Profiles + Actuator (Prod Readiness) ✅ DONE

## Day 30 Objective

Connect:

```text
Days 1–29 — app works on your machine (manual schema, one application.properties)
        ↓
Day 30 — make it production-shaped: versioned DB migrations, env-specific config, health checks
```

Core question:

> **How do teams share schema changes safely, run the same app in dev vs prod with different settings, and know if the app is healthy — without SSH-ing into the server?**

In scope:

- Flyway — versioned SQL migrations vs HeidiSQL / ddl-auto
- `db/migration/V{n}__description.sql`; baseline for existing DB
- `ddl-auto=none` when Flyway owns schema; `flyway-mysql` for MySQL (Flyway 11+)
- Profiles — `application-dev.properties` + `spring.profiles.active`
- Actuator — `/actuator/health`; expose only what you need; Security `permitAll` for health

Out of scope (later):

- Liquibase deep dive
- Custom HealthIndicator / K8s probe deep dive
- Day 28 `@SpringBootTest` (still on hold)

Jira ticket: created.

---

# Day 30 Experiment 1 — WHAT + WHY ✅ DONE

- Definitions first: Flyway / Profiles / Actuator.
- Q1–Q3: team schema sync; env config without code change; ready-made health endpoints. ✅

# Day 30 Experiment 2 — Flyway ✅ DONE

- `spring-boot-starter-flyway` + **`flyway-mysql`** (without it → `Unsupported Database: MySQL 8.0`).
- Baseline existing DB + `V2__add_employee_notes.sql` → `notes` column + `flyway_schema_history`. ✅

# Day 30 Experiment 3 — Profiles ✅ DONE

- Shared `application.properties` + `application-dev.properties`.
- `spring.profiles.active=dev`. ✅

# Day 30 Experiment 4 — Actuator ✅ DONE

- `spring-boot-starter-actuator` + `management.endpoints.web.exposure.include=health`.
- Security: `/actuator/health` → `permitAll`.
- `GET /actuator/health` → `{"status":"UP"}` without auth. ✅

---

# Day 30 — God-Level Notes (Notebook)

## Why this day?

Working on one laptop ≠ production-ready. Teams need:

| Need | Tool |
|------|------|
| Schema in Git, applied the same everywhere | **Flyway** |
| Same JAR, different DB/settings per env | **Profiles** |
| “Is the app up?” without SSH | **Actuator** |

---

## Flyway — definition

**Flyway** = database migration tool. Versioned SQL files in the project run **in order, once each**, tracked in `flyway_schema_history`.

```text
src/main/resources/db/migration/
  V2__add_employee_notes.sql
       ↓
App start → Flyway migrate → ALTER TABLE … (if not yet applied)
```

**Naming:** `V{version}__{description}.sql` — **double underscore** required.

| Approach | Problem |
|----------|---------|
| HeidiSQL by hand | Not in Git; teammate/prod diverge |
| `ddl-auto=update` | Opaque, unsafe in prod |
| **Flyway** | Explicit, reviewable, repeatable |

**Interview:** *“Flyway version-controls the DB schema like Git version-controls code.”*

### Existing DB (what we did)

Tables already existed → `baseline-on-migrate=true` + `baseline-version=1` (treat current DB as V1) → first real script = **V2**.

### Hibernate + Flyway

```properties
spring.jpa.hibernate.ddl-auto=none   # or validate
```

Flyway owns schema changes. Hibernate must not invent columns.

### Flyway 11 + MySQL (Boot 4 gotcha)

```xml
spring-boot-starter-flyway
flyway-mysql          <!-- required or: Unsupported Database: MySQL 8.0 -->
```

---

## Profiles — definition

**Profile** = named environment (`dev`, `test`, `prod`). Spring loads matching property files.

```text
application.properties          → always
application-dev.properties      → if profile=dev
application-test.properties     → if profile=test (Day 26 tests)
```

```properties
spring.profiles.active=dev
```

Log proof: `The following 1 profile is active: "dev"`

**Interview:** *“Profiles switch configuration per environment without changing Java code.”*

| Profile | Typical use |
|---------|-------------|
| `dev` | Local MySQL, verbose logging |
| `test` | H2 for `@DataJpaTest` |
| `prod` | Cloud DB, secrets, quiet logs |

Never commit prod passwords as the only shared config everyone uses blindly — use profiles + env vars / secrets in real teams.

---

## Actuator — definition

**Actuator** = production-ready ops endpoints (health, metrics, info) without writing controllers.

```text
GET /actuator/health  →  {"status":"UP"}
```

```properties
management.endpoints.web.exposure.include=health
```

Expose **only** what you need — not `*`.

### Security

With Spring Security, health needs:

```java
.requestMatchers("/actuator/health").permitAll()
```

Load balancers / K8s probe health **without** login. Other actuator endpoints stay locked.

**Interview:** *“Actuator exposes health/ops endpoints; in prod expose minimally and secure the rest.”*

---

## How the three fit

```text
Same code (JAR)
    ↓
Profile      → which DB / logging
Flyway       → apply pending migrations to that DB
Actuator     → is process UP (and often DB reachable)
```

---

## What we proved

| Piece | Result |
|-------|--------|
| Flyway V2 | `employees.notes` + `flyway_schema_history` |
| Restart | V2 not re-run |
| Profile `dev` | Active in startup log |
| `/actuator/health` | `UP` without auth |

---

## Hard rules (memorize)

1. **Flyway** = versioned SQL in Git; history table = applied once.
2. Name: `V2__description.sql` (double `_`).
3. With Flyway → `ddl-auto=none` or `validate`.
4. Existing DB → `baseline-on-migrate` (or equivalent strategy).
5. MySQL + Flyway 11 → add **`flyway-mysql`**.
6. **Profiles** = env-specific properties; activate with `spring.profiles.active`.
7. **Actuator** = ops endpoints; expose `health` only by default habit.
8. Permit `/actuator/health` in Security if LBs need unauthenticated probes.

---

## Decision tree

```text
Schema change?
├─ Write V{n}__….sql under db/migration
├─ Commit to Git
└─ Deploy → Flyway applies on startup

Different env settings?
└─ application-{profile}.properties + spring.profiles.active

Need health check?
└─ Actuator + expose health + Security permitAll for /actuator/health
```

---

## What's next

| Topic | Status |
|-------|--------|
| Day 28 `@SpringBootTest` + service unit tests | ⏸ ON HOLD |
| JWT / DB UserDetailsService | When project needs it |
| Curriculum core | Days 1–27, 29–30 done |

Day 30 = prod readiness basics — migrations, env config, health. Enough for interviews and real teams; deepen when deploying for real.

Ready for day review → God-level notes → Jira Done.

---

# Spring Security Deep Dive (5 YOE track) — ROADMAP

Day 29 = **operator level** (protect APIs, AuthN vs AuthZ, Basic, roles).

This track = **architect / senior interview level** — internals, request lifecycle, how pieces connect.

**Honest scope:** “Know everything in Spring Security” is impossible (OAuth2, SAML, LDAP, reactive, …).  
**5 YOE target** = you can draw the architecture, explain the request path, debug 401/403, design session vs JWT, and answer senior interview questions without memorizing only annotations.

| Day | Theme | Outcome |
|-----|--------|---------|
| **31** | Architecture & request lifecycle | FilterChainProxy, SecurityFilterChain, filter order, where AuthN/AuthZ sit |
| **32** | Authentication internals | Authentication, AuthenticationManager, Provider, UserDetailsService, SecurityContextHolder |
| **33** | Authorization internals | AuthorizationManager / filter authorization, method security proxy, EntryPoint vs AccessDeniedHandler |
| **34** | Stateless APIs (JWT architecture) | No session, Bearer token, custom filter placement, when JWT vs Basic |
| **35** (optional) | OAuth2 Resource Server + interview scenarios | JWT validation via Spring, common senior Q&A |

Still out of scope unless you ask later: full Authorization Server, SAML, LDAP, WebFlux Security, complex multi-tenant IAM.

Day 28 testing remains ⏸ ON HOLD.

---

# Day 31 — Security Architecture & Request Lifecycle ✅ DONE

## Day 31 Objective

Connect:

```text
Day 29 — you configured SecurityFilterChain (what to allow)
        ↓
Day 31 — what Spring actually builds and runs on every request (how / internals)
```

Core question:

> **From the moment an HTTP request hits Tomcat until your `@RestController` runs — what does Spring Security do, in what order, and which objects hold “who is the user”?**

Jira ticket: created.

---

# Day 31 Experiment 1 — Architecture + see filters ✅ DONE

- FilterChainProxy vs SecurityFilterChain vs Filters.
- Filters created by `http.build()`, not handwritten `Filter` classes.
- `@EnableWebSecurity(debug = true)` printed chain:
  `DisableEncodeUrlFilter, WebAsyncManagerIntegrationFilter, SecurityContextHolderFilter, HeaderWriterFilter, LogoutFilter, BasicAuthenticationFilter, RequestCacheAwareFilter, SecurityContextHolderAwareRequestFilter, AnonymousAuthenticationFilter, ExceptionTranslationFilter, AuthorizationFilter`
- No CsrfFilter — `csrf.disable()`.

# Day 31 Experiment 2 — Map config → filters + understanding ✅ DONE

- Q1: AuthorizationFilter after Basic — need identity before AuthZ. ✅
- Q2: No CsrfFilter — disabled. ✅
- Q3: Printed list = filters **inside** SecurityFilterChain; FilterChainProxy is the outer servlet filter (not in that list). ✅

---

# Day 31 — God-Level Notes (Notebook)

## Big picture

```text
HTTP → Tomcat → FilterChainProxy → SecurityFilterChain (ordered Filters)
     → DispatcherServlet → @RestController
```

Security is a **servlet Filter layer** in front of MVC. Controllers stay unchanged.

---

## Three names (never mix)

| Name | Role |
|------|------|
| **FilterChainProxy** | The **one** Servlet filter registered with Tomcat (`springSecurityFilterChain`). Front door. |
| **SecurityFilterChain** | Your `@Bean` — ordered list of Security filters + URL matching. Checklist behind the door. |
| **Filter** (e.g. Basic, Authorization) | One step in that checklist. Created by `HttpSecurity` DSL + `build()`. |

**Debug list** (`@EnableWebSecurity(debug=true)`) shows **SecurityFilterChain** contents — **not** FilterChainProxy itself.

---

## How filters appear without writing `implements Filter`

| Your config | What gets added |
|-------------|-----------------|
| `spring-boot-starter-security` | Registers FilterChainProxy in container |
| `@Bean SecurityFilterChain` + `http.build()` | Builds internal filter list |
| `.httpBasic(...)` | **BasicAuthenticationFilter** |
| `.authorizeHttpRequests(...)` | **AuthorizationFilter** |
| `.csrf(disable)` | **No** CsrfFilter |

Config → Spring instantiates filters. Not hidden magic — generated.

---

## Your observed chain (order = execution order)

```text
SecurityContextHolderFilter     → prepare SecurityContext (ThreadLocal)
HeaderWriterFilter
LogoutFilter
BasicAuthenticationFilter       → AUTHENTICATION (who)
…
AnonymousAuthenticationFilter   → if no user → anonymous principal
ExceptionTranslationFilter      → map security failures → 401 / 403
AuthorizationFilter             → AUTHORIZATION (allowed?)
→ Controller
```

| Concern | Filter |
|---------|--------|
| Who are you? | BasicAuthenticationFilter (+ UserDetailsService / AuthenticationManager — Day 32) |
| Allowed? | AuthorizationFilter |
| HTTP status for failures | ExceptionTranslationFilter → EntryPoint (401) / AccessDeniedHandler (403) |

---

## SecurityContext

| Object | Role |
|--------|------|
| `Authentication` | Principal + authorities + authenticated flag |
| `SecurityContext` | Holds current `Authentication` |
| `SecurityContextHolder` | Access: `getContext().getAuthentication()` — default **ThreadLocal** |

Cleared after request so threads don’t leak identity.

---

## 401 vs 403 (in the chain)

| Status | Meaning | Typical stage |
|--------|---------|----------------|
| **401** | Not authenticated | AuthN missing/failed → ExceptionTranslationFilter / EntryPoint |
| **403** | Authenticated, not allowed | AuthorizationFilter deny → AccessDeniedHandler |

---

## Method security vs filter AuthZ

```text
URL rules     → AuthorizationFilter (before controller)
@PreAuthorize → AOP on service (after controller) — Day 33
```

---

## Hard rules (memorize)

1. **FilterChainProxy** = servlet door; **SecurityFilterChain** = ordered checklist.
2. You configure DSL; **`build()`** creates Filters.
3. See list: `@EnableWebSecurity(debug = true)` (dev only).
4. AuthN filters **before** AuthZ filter.
5. Current user = **SecurityContextHolder** (ThreadLocal).
6. Debug print ≠ FilterChainProxy; it prints inner chain.

---

## 90-second interview answer

> Spring Security registers a FilterChainProxy in front of DispatcherServlet. That proxy runs a SecurityFilterChain — filters built from SecurityFilterChain bean. BasicAuthenticationFilter authenticates and stores Authentication in SecurityContextHolder. AuthorizationFilter enforces URL rules. ExceptionTranslationFilter maps failures to 401/403. Method security is a later AOP check on the service.

---

## What's next — Day 32

**Authentication internals:** `Authentication` object, `AuthenticationManager`, `AuthenticationProvider`, `DaoAuthenticationProvider`, how Basic filter calls them, password check with BCrypt.

Day 31 = architecture of the gate. Day 32 = how “login” actually works inside the gate.

Ready for day review → God-level notes → Jira Done.

---

# Day 32 — Authentication Internals (Manager / Provider / UserDetails / Context) ✅ DONE

## Day 32 Objective

Connect:

```text
Day 31 — BasicAuthenticationFilter sits in the chain (where AuthN runs)
        ↓
Day 32 — Authentication → Manager → Provider → UserDetailsService → PasswordEncoder → SecurityContext
```

Core question:

> **When Postman sends `Authorization: Basic …`, what objects are created and which classes decide “password OK” vs 401 — step by step?**

Jira ticket: created.

---

# Day 32 Experiment 1 — AuthN object model + flow ✅ DONE

- Flow taught: Filter → Manager → Provider → UserDetailsService + PasswordEncoder → SecurityContext.
- Q1: Manager = facade / entry point; Provider = actual verification strategy (clarified after “not sure”). ✅
- Q2: UserDetailsService loads user by username (e.g. in-memory `vijendra` → UserDetails). ✅
- Q3: Password not kept in SecurityContext after success — credentials cleared; only needed for verify. ✅
- Follow-up: AuthenticationManager (interface) vs ProviderManager (impl); plain-English meaning of “provider”; why username/password is a provider; OAuth IdP vs Spring AuthenticationProvider; why multiple providers.

---

# Day 32 — God-Level Notes (Notebook)

## AuthN flow (Basic Auth)

```text
Authorization: Basic …
    → BasicAuthenticationFilter
    → unauthenticated Authentication (username + password)
    → AuthenticationManager.authenticate(...)   // interface
    → ProviderManager                           // the usual implementation
         → AuthenticationProvider(s)
         → DaoAuthenticationProvider
              → UserDetailsService.loadUserByUsername
              → PasswordEncoder.matches(raw, hash)
    → authenticated Authentication
    → SecurityContextHolder.setAuthentication(...)
```

Failure → `BadCredentialsException` → ExceptionTranslationFilter → **401**.

---

## AuthenticationManager vs ProviderManager vs AuthenticationProvider

Three names — don’t treat them as siblings.

| Name | What it is | Role |
|------|------------|------|
| **`AuthenticationManager`** | **Interface** | Contract filters call: `authenticate(token)` |
| **`ProviderManager`** | **Default implementation** of that interface | Orchestrates a **list** of `AuthenticationProvider`s |
| **`AuthenticationProvider`** | Strategy / worker | Actually verifies one kind of credentials |

```text
AuthenticationManager     ← interface (“can authenticate”)
        ▲
        │ implements
ProviderManager           ← “manager that uses Providers” (NOT “a type of Provider”)
        │
        │ has list of
        ▼
AuthenticationProvider(s) ← DaoAuthenticationProvider, JWT, LDAP, …
```

**Naming trap:** `ProviderManager` sounds like a sibling of “Provider.” It is not.  
It means: **a Manager that coordinates AuthenticationProviders**.

Mental rename (for clarity only):

```text
AuthenticationManager  ≈ authenticator role (interface)
ProviderManager        ≈ provider-list authenticator (implementation)
AuthenticationProvider ≈ username-password checker / JWT checker / …
```

**Other Manager implementations?** In practice for servlet apps, **`ProviderManager` is the one**. Custom managers / wrappers are rare. Reactive uses a parallel API (`ReactiveAuthenticationManager`). Interview answer: *“I use ProviderManager; I swap Providers, not Managers.”*

**Manager does not check the password.** Provider does (via UserDetailsService + PasswordEncoder for DAO).

You rarely implement Manager. You supply **UserDetailsService** + **PasswordEncoder**; Boot wires **DaoAuthenticationProvider** into **ProviderManager**.

```text
Filter → AuthenticationManager (ProviderManager)
              ├─ DaoAuthenticationProvider   ← username/password (our app)
              ├─ Jwt… Provider               ← if configured
              └─ Ldap… Provider              ← if configured
         first provider that supports(token) runs
```

---

## What does “provider” mean? (plain English)

Forget Spring jargon for a second.

**Provider** = something that **supplies a specific service**.

Here the service is: **verify identity for one kind of proof**.

So **`AuthenticationProvider`** = a **plug-in / specialist that knows how to check one style of login**.

| Proof the client sends | Specialist that verifies it |
|------------------------|-----------------------------|
| Username + password | `DaoAuthenticationProvider` |
| JWT | JWT-oriented provider / resource-server support |
| LDAP bind | LDAP provider |

**Why is username + password “a provider”?**  
Because that pair is still **one way of proving who you are**. Spring needs a component whose job is to verify **that** way — not only “social login.”

```text
“I claim I’m vijendra, password = …”
        ↓
DaoAuthenticationProvider
        ↓
load user + check password hash → yes/no
```

---

## “Provider” in social login vs Spring (same word, different layer)

| Term | Meaning |
|------|---------|
| **Identity Provider (IdP)** — Google, GitHub, Okta | **External** system that owns the user account and says “this person logged in with us” |
| **Spring `AuthenticationProvider`** | **Inside your app** — class that validates one credential/token type |

```text
Social login (big picture)
  User → Google (IdP) → your app receives a token
              ↓
  Your app → Spring AuthenticationProvider (or OAuth support)
             accepts/verifies that token → SecurityContext
```

Related idea (identity). **Not** the same object as `DaoAuthenticationProvider`.

---

## Why multiple Providers? (why the abstraction)

One Manager, many Providers = **Strategy pattern**: same door, different ID checks.

| Scenario | Providers |
|----------|-----------|
| Our app today | Only `DaoAuthenticationProvider` |
| Password + JWT API | Dao + JWT provider |
| Local admin + company LDAP | Dao + LDAP provider |

If BasicAuthenticationFilter hardcoded “InMemory + BCrypt,” adding JWT/LDAP would mean rewriting the filter. Instead:

```text
Filter → Manager.authenticate(token)   // stable
              └── whichever Provider supports that token
```

---

## Authentication object (two lives)

| Moment | Content | authenticated |
|--------|---------|---------------|
| Input to manager | principal=username, credentials=password | `false` |
| Output on success | principal=UserDetails, authorities=roles, credentials cleared | `true` |

---

## Your beans in the flow

| Bean | Step |
|------|------|
| `UserDetailsService` (InMemory…) | Load user + hash + roles by username |
| `PasswordEncoder` (BCrypt) | `matches(raw from header, hash from UserDetails)` |
| `SecurityFilterChain` + `.httpBasic()` | Installs BasicAuthenticationFilter that calls the manager |

---

## SecurityContext after success

- Holds authenticated `Authentication` (who + roles).
- **Password should not remain** — credentials cleared after verify (avoid leaking secrets in memory/session dumps).
- Later: AuthorizationFilter / `@PreAuthorize` read authorities from context — not the password.

---

## Hard rules (memorize)

1. Filter → **AuthenticationManager** → **Provider** → UserDetailsService + PasswordEncoder → SecurityContext.
2. `AuthenticationManager` = interface; **`ProviderManager` = the usual implementation** (manages Providers — not a Provider itself).
3. **Provider** = plug-in that verifies **one style** of login (password is one style).
4. OAuth **IdP** (Google) ≠ Spring **AuthenticationProvider** (in-app checker).
5. Multiple Providers = multiple identity-check strategies under one Manager.
6. Success → context filled; failure → **401** (not 403).
7. Never store/compare plain passwords; BCrypt `matches`; password not kept in context after AuthN.

---

## 90-second interview answer

> BasicAuthenticationFilter builds an unauthenticated token and calls AuthenticationManager. In practice that is a ProviderManager, which delegates to an AuthenticationProvider such as DaoAuthenticationProvider. That provider loads UserDetails and checks the password with PasswordEncoder. On success, authenticated Authentication goes into SecurityContextHolder; credentials are cleared. On failure, BadCredentialsException becomes 401. “Provider” means a verification strategy for one credential type — username/password is one strategy; Google as an Identity Provider is an external identity source at a different layer.

---

## What's next — Day 33

**Authorization internals:** AuthorizationFilter, how `requestMatchers` / `hasRole` are evaluated, AccessDeniedHandler vs AuthenticationEntryPoint, `@PreAuthorize` / method security AOP proxy.

Day 32 = how identity is proven. Day 33 = how permission is decided.

Ready for day review → God-level notes → Jira Done.

---

# Day 33 — Authorization Internals (Filter AuthZ / 401–403 / Method Security AOP) ✅ DONE

## Day 33 Objective

Connect:

```text
Day 31 — AuthorizationFilter sits at the end of the chain (where AuthZ runs)
Day 32 — Authentication lives in SecurityContext (who you are)
        ↓
Day 33 — AuthorizationManager decides allowed?; EntryPoint vs AccessDeniedHandler; @PreAuthorize AOP
```

Core question:

> **After identity is known (or anonymous) — who decides “allowed?”, how do `hasRole` / `permitAll` work, why is it 401 vs 403, and when does `@PreAuthorize` fire relative to the controller?**

Jira ticket: created.

---

# Day 33 Experiment 1 — AuthZ object model (teach first) ✅ DONE

- AuthorizationFilter = path + method + Authentication from context; no PasswordEncoder.
- AuthN path (Basic → Manager → Provider) does password check; AuthZ only reads authorities.
- USER delete → **403** / AccessDeniedHandler; anonymous + protected → **401** / AuthenticationEntryPoint.
- `@PreAuthorize` runs when service proxy is invoked — after controller method has started (on the service call).
- Q1–Q3 answered correctly (naming: BasicAuthenticationFilter / Provider, not “AuthenticationFilter”).

# Day 33 Experiment 2 — Map real requests to AuthZ path ✅ DONE

| Case | Rule | Result |
|------|------|--------|
| A GET no auth | 1st `permitAll` | allow → 200 |
| B POST no auth | 3rd `hasAnyRole` | deny → 401 |
| C POST USER | 3rd | allow → 201 |
| D DELETE USER | 2nd `hasRole(ADMIN)` | deny → 403 |
| E DELETE ADMIN | 2nd | allow → 204; `@PreAuthorize` also runs |
| URL DELETE rule removed, `@PreAuthorize` kept | | USER still blocked at service proxy |

# Day 33 Experiment 3 — EntryPoint vs AccessDeniedHandler ✅ DONE

- Both are **handlers**, not exception type names.
- `AuthenticationEntryPoint` → commence auth / **401** (anonymous or AuthN fail).
- `AccessDeniedHandler` → handle `AccessDeniedException` / **403** (known user, not allowed).
- Wrong Basic password → AuthN fail → **401** (not AuthZ).

# Day 33 Experiment 4 — Method security AOP / proxy ✅ DONE

- `@PreAuthorize` runs only when the **proxied** service method is invoked; if controller never calls service → annotation never fires.
- Annotation = metadata; enforcement = AOP interceptor on the Spring proxy.
- Self-invocation (`this.deleteEmployee`) bypasses proxy → check skipped (same trap as `@Transactional`).

---

# Day 33 — God-Level Notes (Notebook)

## AuthZ after AuthN (big picture)

```text
SecurityContext has Authentication (or anonymous)
    → AuthorizationFilter
         → AuthorizationManager (built from authorizeHttpRequests)
         → match requestMatchers (order matters)
         → allow / deny
    → (if allow) Controller
         → Service PROXY
              → @PreAuthorize (method security)
              → real method
```

Day 32 = prove identity. Day 33 = decide permission. Password is **not** re-checked at AuthZ.

---

## URL AuthZ pieces

| Piece | Role |
|-------|------|
| `.authorizeHttpRequests(...)` | Declares rules; wired at `http.build()` |
| **`AuthorizationManager`** | Decision engine: request + Authentication → yes/no |
| **`AuthorizationFilter`** | Filter that invokes that manager (last in your chain) |

Looks at: **HTTP method + path + authorities** in SecurityContext. Not PasswordEncoder.

---

## Roles vs authorities

| API | Meaning |
|-----|---------|
| `.roles("ADMIN")` | stores authority `ROLE_ADMIN` |
| `.hasRole("ADMIN")` | checks `ROLE_ADMIN` |
| `.hasAuthority("ROLE_ADMIN")` | same check, explicit |

---

## Your request matrix (locked)

| Request | Auth | Rule | Status |
|---------|------|------|--------|
| GET `/employees` | none | `permitAll` | 200 |
| POST `/employees` | none | `hasAnyRole` | **401** |
| POST `/employees` | USER | `hasAnyRole` | 201 |
| DELETE | USER | `hasRole(ADMIN)` | **403** |
| DELETE | ADMIN | URL + `@PreAuthorize` | 2xx |

---

## 401 vs 403 — handlers (not exception names)

`ExceptionTranslationFilter` maps security failures to HTTP:

| Condition | Handler | Status |
|-----------|---------|--------|
| Not really authenticated (anonymous / AuthN fail) | **`AuthenticationEntryPoint`** | **401** |
| Authenticated, missing permission | **`AccessDeniedHandler`** (`AccessDeniedException`) | **403** |

```text
Wrong password     → AuthN fail → EntryPoint → 401
USER lacks ADMIN   → AuthZ deny → AccessDeniedHandler → 403
```

Anonymous still has an Authentication (from `AnonymousAuthenticationFilter`).  
`permitAll` allows it; `authenticated` / `hasRole` do not → EntryPoint path.

---

## Method security (second gate)

| | URL AuthZ | Method AuthZ |
|--|-----------|--------------|
| Where | `AuthorizationFilter` | AOP on service bean |
| When | Before controller | After controller starts, on service call |
| Config | `authorizeHttpRequests` | `@EnableMethodSecurity` + `@PreAuthorize` |

Delete flow with ADMIN:

```text
AuthorizationFilter (ADMIN) → Controller → EmployeeService proxy
  → @PreAuthorize("hasRole('ADMIN')") → delete body
```

Defense in depth: remove URL DELETE rule → `@PreAuthorize` still blocks USER.

**Proxy rule:** only calls through the Spring bean hit `@PreAuthorize`.  
`this.method()` inside the same class → no proxy → annotation skipped.

---

## Hard rules

1. AuthZ reads SecurityContext authorities — does not verify passwords.
2. First matching `requestMatchers` wins — order matters.
3. 401 = EntryPoint (prove identity); 403 = AccessDeniedHandler (known, forbidden).
4. URL gate ≠ method gate; use both for sensitive ops when it helps.
5. `@PreAuthorize` needs the proxy; self-invocation bypasses it.
6. `hasRole("X")` ⇒ authority `ROLE_X`.

---

## 90-second interview answer

> After Authentication is in SecurityContext, AuthorizationFilter uses an AuthorizationManager built from authorizeHttpRequests — matching method, path, and roles/authorities. Failures go through ExceptionTranslationFilter: unauthenticated → AuthenticationEntryPoint → 401; authenticated but forbidden → AccessDeniedHandler → 403. Method security (@PreAuthorize) is a second AOP check on the service proxy after the controller calls it — defense in depth, same self-invocation caveat as @Transactional.

---

## What's next — Day 34

**Stateless APIs / JWT architecture:** no session, Bearer token, where a JWT filter sits in the chain, when JWT vs Basic.

Day 33 = how permission is decided. Day 34 = how APIs prove identity without server sessions.

Ready for day review → mark Jira **Done**.

---

# Day 34 — Stateless APIs & JWT Architecture (Bearer / no server session) ✅ DONE

## Day 34 Objective

Connect:

```text
Days 31–33 — Filter chain, AuthN → SecurityContext, AuthZ → 401/403
        ↓
Day 34 — Prove identity with a token (Bearer/JWT), not a server session
```

Core question:

> **How does a REST API authenticate without storing a login session on the server — and where does a JWT/Bearer check sit so AuthorizationFilter and `@PreAuthorize` still work the same?**

Jira ticket: created.

---

# Day 34 Experiment 1 — Session vs Stateless + Bearer (teach first) ✅ DONE

- AuthZ unchanged after JWT fills SecurityContext — AuthorizationFilter / `@PreAuthorize` same as Basic.
- JWT payload is Base64-encoded (readable), not encrypted — never put passwords/secrets in claims.
- JWT/Bearer filter runs **before** AuthorizationFilter (identity first, then allowed?).

# Day 34 Experiment 2 — Login + API request flow ✅ DONE

- PasswordEncoder only at **login** (issue token); API calls validate JWT, not password.
- Expired / invalid token → unauthenticated → **401** (EntryPoint), not 403.
- AuthZ unchanged: reads roles/authorities from SecurityContext, however it was filled (Basic or JWT).

# Day 34 Experiment 3 — STATELESS, CSRF, Basic vs JWT judgment ✅ DONE

- STATELESS: no server session / no need to store login state for each request; token is the proof.
- Classic CSRF targets **cookies**; Bearer-in-header APIs → CSRF often disabled (as you already do).
- Basic still OK: few trusted clients / learning / password-per-call acceptable.

---

# Day 34 — God-Level Notes (Notebook)

## Big picture

```text
Days 31–33  Filter → AuthN → SecurityContext → AuthZ (401/403)
Day 34      Same pipeline; AuthN proof = Bearer JWT (stateless), not password every time
```

AuthZ does **not** change. Only **how** SecurityContext gets filled changes.

---

## Stateful vs Stateless

| | Session (stateful) | JWT / Bearer (stateless) |
|--|--------------------|---------------------------|
| Server stores | Session “user logged in” | Usually nothing per login |
| Client sends | `JSESSIONID` cookie | `Authorization: Bearer …` |
| Scale | Session store / sticky | Any node validates token |

`SessionCreationPolicy.STATELESS` = don’t create HttpSession for Security; each request brings its own proof.

---

## Basic vs Bearer

```text
Authorization: Basic  base64(user:password)   ← credentials every request
Authorization: Bearer eyJhbGciOi…             ← signed token after login
```

| | Basic | JWT Bearer |
|--|-------|------------|
| Filter | BasicAuthenticationFilter | JWT / Bearer filter (before AuthorizationFilter) |
| PasswordEncoder | every request | **login only** |
| Typical | learning / internal | SPA, mobile, multi-service |

---

## Two-step JWT flow

```text
1) POST /login  → verify password → issue signed JWT (sub, roles, exp)
2) API call     → Bearer token → validate sig + exp → SecurityContext
                → AuthorizationFilter / @PreAuthorize (unchanged)
```

JWT shape: `header.payload.signature`  
Payload is **encoded (readable), not encrypted** — never put passwords/secrets in claims.

Expired / bad token → unauthenticated → **401**.  
Valid user, wrong role → **403** (Day 33 unchanged).

---

## Filter placement

```text
… Jwt/Bearer AuthN filter … ExceptionTranslationFilter → AuthorizationFilter
```

Identity **before** AuthZ — same slot idea as Basic.

---

## CSRF reminder

Cookie-session browser apps → CSRF matters.  
Pure API + `Authorization` header (Basic/Bearer), no session cookie auth → often `csrf.disable()`.

---

## Refresh token (awareness)

Access JWT = short-lived, used on APIs.  
Refresh token = renew access without re-entering password. Not implemented this day.

---

## When to choose what

| Prefer Basic | Prefer JWT / Bearer |
|--------------|---------------------|
| Few clients, learning, demos | Many clients, SPA/mobile, services |
| Password each call OK | Short-lived token, no session store |
| | SSO / OAuth2 IdP (Day 35 Resource Server) |

---

## Hard rules

1. JWT AuthN fills SecurityContext; AuthZ rules stay the same.
2. Validate Bearer **before** AuthorizationFilter.
3. Password check at login; API calls check token, not password.
4. Invalid/expired token → **401**; wrong role → **403**.
5. Don’t put secrets in JWT claims (payload is readable).
6. Stateless API ≈ STATELESS sessions + Bearer; CSRF usually off for header auth.

---

## 90-second interview answer

> Stateful auth stores a server session; stateless APIs use Bearer tokens so each request carries proof. JWT is header.payload.signature — claims like sub, roles, exp; signature proves integrity. Login verifies password and issues a token; later calls validate the JWT into SecurityContext. AuthorizationFilter and @PreAuthorize stay the same. Bad/expired token is 401; wrong role is 403. Basic is fine for simple/internal apps; JWT fits SPA, mobile, and multi-service designs.

---

## What's next — Day 35 (optional)

**OAuth2 Resource Server:** Spring validates JWTs for you; wire Bearer into your Employee API; senior interview scenarios.

Day 34 = architecture. Day 35 = Spring’s standard way to **consume** JWTs.

Ready for day review → mark Jira **Done**.

---

# Day 35 — OAuth2 Resource Server (Spring validates JWTs) + Interview Q&A ✅ DONE

## Day 35 Objective

Connect:

```text
Day 34 — JWT / Bearer architecture (hand-drawn filter idea)
        ↓
Day 35 — Spring oauth2-resource-server does the validation for you
```

Core question:

> **In OAuth2 terms, what is my Employee API — and how does Spring validate a Bearer JWT from an IdP so AuthorizationFilter / `@PreAuthorize` still work?**

Jira ticket: created.

---

# Day 35 Experiment 1 — AS / RS / Client (teach first) ✅ DONE

- Day 35 design: Employee API = **Resource Server**; AS issues JWT.
- Today with Basic: protects APIs **and** checks password itself — not OAuth2 RS yet (no external token).
- RS validates Bearer JWT (sig/exp/issuer); does **not** need user password — identity proven at AS.

# Day 35 Experiment 2 — Spring Resource Server wiring ✅ DONE (teach)

- `issuer-uri`: trust only tokens from that AS (iss + JWKS); not “anyone’s JWT”.
- Valid JWT + missing ADMIN → still **403** via AuthorizationFilter / `@PreAuthorize`.
- Q3 clarified: with JWT, authorities usually come from **token claims** (not UserDetailsService each call) — mapping `roles`/`scope` → `ROLE_…` / `SCOPE_…` matters for `hasRole`.

# Day 35 Experiment 3 — Senior interview drill ✅ DONE

1. FilterChainProxy = Security’s Servlet filter entry; SecurityFilterChain = ordered list of Security filters.
2. Manager orchestrates; Provider performs identity check (ProviderManager implements AuthenticationManager).
3. 401 → AuthenticationEntryPoint; 403 → AccessDeniedHandler (via ExceptionTranslationFilter) — not `@ExceptionHandler`.
4. Employee API in Day 35 design = **Resource Server**; Basic today ≈ protect APIs + check password yourself (not OAuth2 RS yet).
5. AuthZ independent of AuthN mechanism — path + method + authorities from SecurityContext.

---

# Day 35 — God-Level Notes (Notebook)

## OAuth2 roles

| Role | Job |
|------|-----|
| **Client** | Calls the API (SPA, mobile, another service, Postman) |
| **Authorization Server (AS / IdP)** | Authenticates user; **issues** JWT |
| **Resource Server (RS)** | Protects API; **validates** Bearer JWT |

```text
Client → AS (get token) → RS (API + Authorization: Bearer …)
```

Day 35 design: **Employee API = Resource Server**.  
Today’s Basic app: secured API that checks password **itself** — similar job, not OAuth2 RS yet.

---

## Spring wiring (standard path)

```text
spring-boot-starter-oauth2-resource-server
  + .oauth2ResourceServer(oauth2 -> oauth2.jwt(...))
  + spring.security.oauth2.resourceserver.jwt.issuer-uri=…
  + STATELESS + csrf.disable() (typical for Bearer APIs)
  + same authorizeHttpRequests / @PreAuthorize
```

| Property | Meaning |
|----------|---------|
| `issuer-uri` | Trust this AS; discover JWKS; check `iss` |
| `jwk-set-uri` | Direct public keys for signature verify |

RS never needs AS private key or user password DB.

---

## Authorities source (the Q3 lock)

| AuthN style | Where authorities come from |
|-------------|----------------------------|
| Basic + UserDetailsService | Your user store / `.roles(...)` |
| JWT Resource Server | **JWT claims** (`scope`/`scp`/`roles`/…) → Authentication |

AuthZ still reads SecurityContext only.  
If claims don’t become `ROLE_ADMIN`, `hasRole("ADMIN")` fails even when JWT is valid → often need **JwtAuthenticationConverter**.

Valid token ≠ allowed operation: signature OK is AuthN; role check is AuthZ (**403**).

---

## Days 31–35 in one line each

| Day | Lock |
|-----|------|
| 31 | FilterChainProxy → SecurityFilterChain → filter order |
| 32 | Manager → Provider → UserDetails → SecurityContext |
| 33 | AuthorizationFilter; EntryPoint 401 vs AccessDeniedHandler 403; `@PreAuthorize` proxy |
| 34 | Stateless Bearer/JWT architecture; AuthZ unchanged |
| 35 | OAuth2 RS = Spring validates JWT from AS |

---

## Interview name fixes (from drill)

| Wrong / fuzzy | Right |
|---------------|-------|
| “ExceptionHandler” for 401/403 | **AuthenticationEntryPoint** (401), **AccessDeniedHandler** (403); chosen by **ExceptionTranslationFilter** |
| Basic app = OAuth2 RS | Basic = password AuthN; OAuth2 RS = Bearer JWT AuthN |

---

## Hard rules

1. AS issues tokens; RS validates them; Client sends Bearer.
2. `oauth2ResourceServer().jwt()` fills the AuthN filter slot (like Basic did).
3. AuthZ rules stay the same if authorities end up correctly in SecurityContext.
4. Claim mapping is the usual production glue (`SCOPE_…` vs `ROLE_…`).
5. Bad/expired/wrong-issuer token → **401**; known principal, missing permission → **403**.

---

## 90-second interview answer

> In OAuth2 the Client calls our API with a Bearer JWT issued by an Authorization Server. Our Spring app is the Resource Server: oauth2-resource-server validates signature and issuer via JWKS from issuer-uri, puts Authentication into SecurityContext, then AuthorizationFilter and @PreAuthorize run as usual. We don’t check the user’s password on each API call — the AS already did that when issuing the token. Authorities often come from JWT claims, so claim-to-ROLE mapping must match hasRole. 401 means unauthenticated/invalid token; 403 means authenticated but not allowed.

---

## Security deep-dive — COMPLETE ✅

```text
31 Architecture → 32 AuthN → 33 AuthZ → 34 JWT architecture → 35 Resource Server
```

Still optional later: live Keycloak/Auth0 issuer, JwtAuthenticationConverter hands-on, Day 28 testing resume.

Mark Jira **Done**.
