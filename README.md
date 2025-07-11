# Chat Combiner Plugin

**Chat Combiner** is a lightweight RuneLite plugin that automatically combines repeated in-game chat messages into a single line with a counter - helping reduce spam while preserving readability.

Ideal for repetitive messages like:
- "You clean the Grimy guam leaf"
- "You catch a fish."
- "You examine the object..."

---

## 🔧 Features

- ✅ Combines repeated identical chat messages
- ✅ Optional counter position (before or after the message)
- ✅ Customize counter color
- ✅ Configurable minimum repeats before combining starts
- ✅ Auto-resets repeat tracking after inactivity

---

## 🛠️ Configuration Options

All options are available in the RuneLite Plugin Panel under **Chat Combiner**:

| Setting | Description |
|--------|-------------|
| **Counter Color** | Choose the color of the repeat counter `(xN)` |
| **Examines & Info** | Combine messages like item/npc/object examine, welcome/info tips |
| **Game Messages** | Combine common game messages like skill actions or feedback |
| **Minimum Repeat Count** | How many identical messages must appear before collapsing |
| **Reset After (ms)** | Time in milliseconds after which the repeat tracking resets |
| **Counter Position** | Show the `(xN)` counter before or after the message (e.g. `(4x) Message` or `Message (4x)`) |

---

## 💬 Example Output

Given `Minimum Repeat Count = 4`:

```text
You clean the Grimy guam leaf
You clean the Grimy guam leaf
You clean the Grimy guam leaf
(4x) You clean the Grimy guam leaf
