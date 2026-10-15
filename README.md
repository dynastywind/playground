# playground

A personal repo for practicing interview coding problems in Java. It is mostly LeetCode algorithm problems, plus object-oriented design (LLD) problems, concurrency problems, and data structures implemented from scratch. Each problem is one class, and no problem depends on another.

## Quick start

Requirements:

- **JDK 26**: `build.gradle.kts` pins the Java toolchain to 26, so JDK 26 must be installed locally for Gradle to find it.
- **A JDK 17+ to launch Gradle**: the wrapper (Gradle 9.8.0) runs on whatever `java` / `JAVA_HOME` points to. It can be the same JDK 26.

```bash
./gradlew compileJava
```

## Running a problem

Classes that have a `main()` run their own sample inputs and print the results. Classes without a `main()` contain only the solution method, ready to paste into LeetCode.

**From the IDE**: click the run icon next to the class. This is the easiest way.

**From the command line**: the project does not apply the `application` plugin, so first add a generic run task to the end of `build.gradle.kts`:

```kotlin
tasks.register<JavaExec>("run") {
    classpath = sourceSets["main"].runtimeClasspath
    mainClass = providers.gradleProperty("mainClass")
}
```

Then pass the class to run:

```bash
./gradlew -q run -PmainClass=org.lyndon.algo.graph.unit.UnitConverter
```

Don't run classes with `java -cp build/classes/java/main ...` directly: many problems use dependencies such as commons-lang3's `Pair`, and without those jars on the classpath you get a `NoClassDefFoundError`.

## Layout

All code lives under `src/main/java`, organized into packages by **topic**, not by problem number.

| Package | Contents |
| --- | --- |
| `org.lyndon.algo.array` | Arrays, two pointers, binary search, prefix sums, sweep line, and more; the largest package |
| `org.lyndon.algo.graph` | BFS/DFS, shortest paths, union-find, minimum spanning trees, topological sort; `eulerian/` holds Eulerian path problems |
| `org.lyndon.algo.tree` | Binary trees, BSTs, and problems solved with segment trees or Fenwick trees |
| `org.lyndon.algo.dp` | Dynamic programming; `stock/` and `stone/` hold problems from the same series |
| `org.lyndon.algo.string` | Strings |
| `org.lyndon.algo.stack` | Stacks and monotonic stacks |
| `org.lyndon.algo.backtrack` | Backtracking and search with pruning |
| `org.lyndon.algo.link` | Linked lists |
| `org.lyndon.algo.meeting` | Meeting rooms, calendars (MyCalendar), CPU/server task assignment, course scheduling |
| `org.lyndon.algo.schedule` | Task scheduling, car pooling, employee free time, and similar; some problems read input from `resources/schedule/` |
| `org.lyndon.algo.heap` / `sort` / `trie` / `bit` / `hash` / `set` / … | Other smaller topics |
| `org.lyndon.algo.concurrency` | Concurrency problems, e.g. LC1114 PrintInOrder, H2O, ZeroEvenOdd, a read-write lock |
| `org.lyndon.design` | Design problems, e.g. LRU cache, rate limiters, load balancer, file system (`fs/`), and various trackers and systems |
| `org.lyndon.datatype` | Data structures from scratch: ring buffer, segment tree, Fenwick tree, trie |
| `org.lyndon.pattern` | Design patterns, e.g. singleton and double-checked locking |
| `org.lyndon.algo.common`, `org.lyndon.utils` | Shared helpers |
| `org.example` | A MyBatis experiment unrelated to the practice problems; see [below](#mybatis-experiment-orgexample) |

## Conventions

- **The problem number goes in the class Javadoc**, e.g. `/** LC1135 */`. To find a class by problem number:

  ```bash
  grep -rl 'LC1135' src/main/java
  ```

- **One class can hold several solutions** so different approaches can be compared. For example, `CyclesInGrid` has both a union-find and a DFS implementation.
- **Input data lives under resources**: problems that need larger inputs keep them in `src/main/resources/`, at a path that mirrors the package (e.g. `algo/graph/unit/facts.txt`), and load them with `getResourceAsStream`.
- **Shared helpers**:
  - `ListNode.from("1->2->3")` builds a linked list quickly, and `.print()` prints it.
  - `PrintUtils.printArray(...)` / `printList(...)` print arrays and lists.
- **Common dependencies**: small value types use Lombok directly (`@AllArgsConstructor`, `@Getter`, etc.); commons-lang3's `Pair` is used when a tuple is needed.
- **Code style**: follow `.editorconfig`, i.e. 4-space indentation, 120-column lines, LF line endings.

## Tests

There is no `src/test` yet; each problem checks itself through its `main()`. JUnit 5 is already configured, so once tests are added under `src/test/java` they run with:

```bash
./gradlew test
```

## MyBatis experiment (`org.example`)

Unrelated to the practice problems: an experiment in using MyBatis to query a JSON column with MySQL's `json_extract`. The entry point is `org.example.Main`, and the SQL is in `src/main/resources/task-mapper.xml`.

- Running it requires a local MySQL (`localhost:3306/lyndon`). The connection settings are in `src/main/resources/mybatis-config.xml`, and the table DDL is in `schema.sql`.
- H2 is also on the classpath; if you'd rather not install MySQL, you can point the data source at an in-memory database.
- Parts of the code are commented out; it is not a complete example.
