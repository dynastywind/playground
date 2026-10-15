# AGENTS.md

Guidance for OpenCode sessions in this repo. `CLAUDE.md` holds the same guidance for Claude Code; keep the two in sync when you edit one.

## Build & run

- Gradle 8.0 via the wrapper — use `./gradlew`, not a system `gradle`.
- **Java 21+ required** (code uses `List.getFirst()` / `SequencedCollection`). No toolchain is pinned in `build.gradle.kts`, so it builds against the system JDK — confirm it's 21+ before assuming a compile error is real.
- `./gradlew build` (compile + test), `./gradlew compileJava` (compile only), `./gradlew test` (JUnit 5 is wired up but `src/test` does not exist, so tests are a no-op).
- **No `application` plugin and no `run` task** — `./gradlew run` fails. Each practice class is normally run from the IDE run gutter. To run one from the CLI, add this to `build.gradle.kts`:

  ```kotlin
  tasks.register<JavaExec>("run") {
      classpath = sourceSets["main"].runtimeClasspath
      mainClass = providers.gradleProperty("mainClass")
  }
  ```
  then `./gradlew run -PmainClass=org.lyndon.algo.CountOfAtoms`.

## Code organization

Two unrelated bodies of code share one source set:

- `org.lyndon.*` — algorithm/data-structure **interview practice; the real purpose of the repo.** Packages are grouped by topic (`algo/graph/`, `algo/schedule/`, `algo/link/`, `algo/dp/`, `algo/sort/`, `datatype/buffer/`, `trie/`, …), not by problem. Shared helpers live in `algo/common`.
- `org.example.*` — a separate MyBatis + MySQL spike querying a JSON column. Scratch; see gotchas below.

One problem = one self-contained class with its own `main()` that loads sample data, runs variants, and prints results. There is no shared driver and no test harness — exercise a class by running its `main()`.

Recurring conventions across `org.lyndon.*`:

- **Inputs come from resource fixtures**, not hardcoded arrays. Load via `getResourceAsStream("/<topic>/<Name>.txt")` with commons-io `IOUtils.toString`; the resource path mirrors the package (e.g. `algo/graph/unit/facts.txt`, `schedule/FewestCPUGivenTasks.txt`). When adding a problem that reads input, add a matching `.txt` under `src/main/resources/`.
- A single class often holds **multiple solution variants** for the same problem (recursive-DFS, BFS, linked-node, …), each with its own helpers, all invoked from `main()`.
- Lombok for small helper/value classes (`@AllArgsConstructor`, `@Getter`, `@Data`); commons-lang3 `Pair` is the go-to tuple. `LinkedListNode.from("1->2->3")` / `.print()` in `algo/common` build and dump lists concisely.

## MyBatis/MySQL spike (`org.example.*`)

Entry point is `Main` — builds a `SqlSessionFactory` from `mybatis-config.xml` and calls `TaskMapper.search(...)` to query a JSON `conf` column via MySQL `json_extract` (see `task-mapper.xml`).

- Connects to a **real MySQL** at `jdbc:mysql://localhost:3306/lyndon` (root/root, in `mybatis-config.xml`). Nothing runs without that DB; `schema.sql` has the `task` table DDL. H2 is also on the classpath if you want an in-memory DB instead.
- DTO classes (`Task`, `SearchRequest`, `SourceRegionInfo`, `YarnQueue`) are Lombok `@Value @Builder`.
- Exploratory and partially commented-out (insert/DDL paths in `Main`); don't assume it's a finished, correct example.

## Environment

- **Not a git repository** — there is no `.git` here. `git status` / `git diff` / etc. will fail; don't attempt version-control operations.
