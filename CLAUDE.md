# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## What this is

A personal **playground for practicing interview coding challenges** in Java. It is a single Gradle module containing two unrelated bodies of code that happen to share one source set:

- `org.lyndon.*` — the algorithm/data-structure/design practice (the real purpose of the repo).
- `org.example.*` — a separate MyBatis + MySQL spike exploring how to query a JSON column. Not interview practice; treat it as scratch.

There are no tests yet (JUnit 5 is wired up but `src/test` does not exist) and the build produces no runnable artifact. Classes with a `main()` run their own sample inputs; many classes have no `main()` and are just the solution method as submitted to LeetCode.

`README.md` is the human-facing overview (setup, layout, conventions). Keep it in sync when changing the build or the package layout.

## Build & run

```bash
./gradlew build          # compile + (currently no-op) test
./gradlew compileJava    # compile only
./gradlew test           # JUnit 5 is configured; add tests under src/test/java
```

- **JDK 26 toolchain.** `build.gradle.kts` pins `languageVersion = 26`, so JDK 26 must be installed for Gradle to find it. Code relies on 21+ APIs such as `List.getFirst()` / `TreeSet.removeFirst()` (`SequencedCollection`).
- **Gradle wrapper is 9.8.0.** It needs a JDK 17+ on `JAVA_HOME`/`PATH` to launch. Gradle 8.x cannot parse recent JDK version strings and fails at startup, so don't downgrade it.
- **Lombok comes only from the `io.freefair.lombok` plugin (9.5.0)**, which adds Lombok to `compileOnly`/`annotationProcessor` itself (currently 1.18.44). Don't add explicit `org.projectlombok:lombok` dependencies; the plugin overrides their version anyway. Older Lombok crashes `javac` on JDK 26 with an `ExceptionInInitializerError` at `compileJava`; if that error shows up after a JDK bump, upgrade the freefair plugin first.

### Running a single practice class

There is **no `application` plugin**, so each class's `main()` is normally run from the IDE's run gutter. To run one from the CLI, add this reusable task to `build.gradle.kts`:

```kotlin
tasks.register<JavaExec>("run") {
    classpath = sourceSets["main"].runtimeClasspath
    mainClass = providers.gradleProperty("mainClass")
}
```

then:

```bash
./gradlew -q run -PmainClass=org.lyndon.algo.graph.unit.UnitConverter
```

Plain `java -cp build/classes/java/main ...` fails for any class that touches commons-lang3/commons-io (`NoClassDefFoundError`), so it needs the runtime classpath. When checking a single solution outside Gradle, compile the one file with `javac` into a scratch dir instead (fine for classes that don't use Lombok).

## How the practice code is organized (`org.lyndon.*`)

Packages are grouped by **topic**, not by problem:

- `algo/<topic>/` — LeetCode-style problems (`array`, `graph`, `tree`, `dp`, `string`, `stack`, `backtrack`, `link`, `meeting`, `schedule`, `heap`, `sort`, `trie`, `concurrency`, …). `array`, `graph`, `tree` and `dp` are by far the largest.
- `design/` — design problems (LeetCode "Design X" plus LLD-style ones such as `fs/`, `lb/`, `rate/`).
- `datatype/` — from-scratch structures (`buffer/RingBuffer`, segment tree, Fenwick tree, trie).
- `pattern/` — design patterns (singleton, DCL).
- `algo/common/`, `utils/` — shared helpers.

One problem = one self-contained class; there is no shared driver.

Conventions that recur across the practice classes:

- **The LeetCode number is in the class Javadoc** as `/** LC1135 */`. Find a problem by number with `grep -rl 'LC1135' src/main/java`.
- **Sample inputs are usually hardcoded in `main()`.** The few classes with larger inputs load a fixture via `getResourceAsStream("/<path>/<Name>.txt")` (with `commons-io` `IOUtils.toString`) from `src/main/resources/`, where the resource path mirrors the package (e.g. `algo/graph/unit/facts.txt`, `schedule/FewestCPUGivenTasks.txt`). When adding such a problem, add the matching `.txt` there.
- A single class often holds **multiple solution variants** for the same problem to compare approaches (e.g. `UnitConverter` has recursive-DFS, BFS, and linked-node solvers; `CyclesInGrid` has union-find and DFS), each with its own helper methods.
- Shared helpers: `algo/common/ListNode.from("1->2->3")` / `.print()` for building and dumping linked lists; `utils/PrintUtils.printArray(...)` / `printList(...)` for printing.
- Lombok is used freely for the small helper/value classes inside problems (`@AllArgsConstructor`, `@Getter`, `@Data`); `commons-lang3` `Pair` is the go-to tuple.
- Formatting follows `.editorconfig`: 4-space indent, 120-column lines, LF.

## The MyBatis/MySQL spike (`org.example.*`)

`Main` is the entry point. It builds a `SqlSessionFactory` from `mybatis-config.xml` and calls `TaskMapper.search(...)`. The point of the spike is querying a JSON `conf` column via MySQL `json_extract` (see the `search` statement in `task-mapper.xml`). Notes if you touch it:

- It connects to a **real MySQL** at `jdbc:mysql://localhost:3306/lyndon` (root/root) — credentials are in `mybatis-config.xml`. Nothing runs without that DB; `schema.sql` has the `task` table DDL. An H2 dependency is also on the classpath if you want to point the datasource at an in-memory DB instead.
- Value/DTO classes (`Task`, `SearchRequest`, `SourceRegionInfo`, `YarnQueue`) are Lombok `@Value @Builder`.
- This area is exploratory and partially commented-out (insert/DDL paths in `Main`); don't assume it's a finished, correct example.
