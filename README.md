# Logger

1. log4j ->> 
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
   By default spring uses Logback

# JpaRepository<T, ID> (Most Common)

Extends PagingAndSortingRepository and CrudRepository.

Adds JPA-specific features like batch operations, flushing, etc.

Extra methods:

List<T> findAll();
List<T> findAllById(Iterable<ID> ids);
void flush();
<S extends T> S saveAndFlush(S entity);
void deleteInBatch(Iterable<T> entities);
void deleteAllInBatch();

Example:

public interface DemoRepository extends JpaRepository<DemoEntity, Long> {}

✔️ Most widely used in real projects
✔️ Includes everything from CRUD + paging/sorting + JPA goodies
✘ Sometimes gives too many methods (can bloat interface)

# Difference between **PUT** and **PATCH**:

### 🔹 **PUT**

- Full update / Replace
- Replaces the **entire resource** with what you send
- Missing fields → overwritten with `null` or defaults
- Idempotent (repeating the same request gives the same result)

👉 Example:
`PUT /demo/1` with `{ "name": "SuperTruck v2" }`
➡️ Other fields like `description` and `status` get erased.

---

### 🔹 **PATCH**

- Partial update
- Updates **only the provided fields**
- Missing fields → left unchanged
- Also idempotent (in practice, but designed for partial changes)

👉 Example:
`PATCH /demo/1` with `{ "name": "SuperTruck v2" }`
➡️ Only `name` is updated, `description` and `status` stay as they are.

---

✅ In simple terms:

- **PUT = replace everything**
- **PATCH = modify what you send**

---

Do you want me to also give you a **real-life analogy** (like editing a profile vs replacing a profile) so it sticks better?
