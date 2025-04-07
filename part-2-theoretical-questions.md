## 1. AWS Design Question

You need to deploy the above microservice on AWS in a highly available and scalable manner. Describe your architecture using AWS services. Consider:

### Compute
- **AWS Fargate** (via ECS): To run containerized microservices without managing servers.
- Alternatively, **Lambda** if the service can be broken down into event-driven functions.
- **Auto Scaling Group (ASG)** on EC2 with Load Balancer (less preferred unless compute customization is needed).

### Database
- **Amazon Aurora (MySQL-compatible)**: Offers high availability, automatic failover, read replicas, and better performance over RDS MySQL.
- Use **Multi-AZ deployment** and **Aurora Replicas** for read scalability.

### Storage
- **Amazon S3**: For static assets, objects, logs, backups.
- **Amazon EFS**: If shared file storage is needed between instances.

### Security
- **IAM Roles and Policies**: For least privilege access to AWS resources.
- **API Gateway**: To manage and secure public-facing APIs.
- **AWS WAF**: Web Application Firewall to prevent common threats (e.g., SQL injection, XSS).
- **Security Groups and NACLs**: To control traffic to EC2/ECS resources.

### Scaling Considerations
- **Application Load Balancer (ALB)**: For routing and distributing incoming traffic.
- **ECS/Fargate Auto Scaling**: Based on CPU/memory utilization or custom metrics.
- **Aurora Auto Scaling**: For read replicas based on load.

---

## 2. Debugging & Performance Optimization

A customer reports that the `/sessions` endpoint takes 10 seconds to return results when querying all sessions. What steps would you take?

### Step-by-step Debugging:

1. **Reproduce the issue**:
    - Check logs, simulate the same request and environment.

2. **Analyze DB Query**:
    - Profile the query: `EXPLAIN SELECT * FROM sessions`.
    - Identify full table scans, missing indexes.

3. **Optimize Database Access**:
    - Add relevant indexes (e.g., on frequently filtered columns).
    - Avoid `SELECT *` — retrieve only needed columns.

4. **Pagination**:
    - Return sessions in chunks using `LIMIT` and `OFFSET` or keyset pagination.

5. **Caching**:
    - Use Redis/Memcached to cache frequent queries.
    - Use HTTP-level caching (e.g., Cache-Control headers or API Gateway cache).

6. **Profiling the Code**:
    - Use tools like X-Ray (AWS) or application profilers to spot bottlenecks.

7. **Concurrency Improvements**:
    - Use connection pooling.
    - Optimize thread usage or async processing if needed.

---

## 3. Security Scenario

Your service is under a DDoS attack. What measures would you take to mitigate it both in code and infrastructure?

### Infrastructure Level

- **Enable AWS Shield (Standard/Advanced)**: Provides DDoS protection.
- **WAF Rules**: Block malicious IPs, rate-limit requests, detect anomalies.
- **API Gateway Throttling**: Set rate and burst limits to protect backends.
- **CloudFront**: CDN with built-in DDoS mitigation and caching.
- **Use ALB with Target Groups**: Helps absorb traffic and scale.

### Code Level

- **Rate Limiting / Throttling**: Implement in the application or via middleware.
- **Graceful Degradation**: Return cached/stale results if under stress.
- **Circuit Breakers**: Avoid cascading failures.

---

## 4. Code Review Question

### Java Code Snippet:

```java
public List<Session> getSessions() {
    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/conferences", "user", "pass");
    Statement stmt = conn.createStatement();
    ResultSet rs = stmt.executeQuery("SELECT * FROM sessions");
    List<Session> sessions = new ArrayList<>();
    while (rs.next()) {
        sessions.add(new Session(rs.getInt("id"), rs.getString("title"), rs.getString("description")));
    }
    return sessions;
}
```

### Issues & Improvements

1. **Resource Leaks**
    - Connections, Statements, and ResultSets are not closed.
    - ✅ **Fix**: Use `try-with-resources` to ensure they are properly closed.

2. **Hardcoded Credentials**
    - Storing sensitive credentials (e.g., DB username/password) directly in code is insecure.
    - ✅ **Fix**: Use environment variables or AWS Secrets Manager to securely manage credentials.

3. **SQL Injection Risk**
    - While the example doesn't take user input, using plain `Statement` can lead to SQL injection if inputs are added later.
    - ✅ **Fix**: Always use `PreparedStatement` for executing SQL queries with parameters.