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
| **Counter Position** | Show the `(xN)` counter before or after the message (e.g. `(4x) Message` or `Message (4x)`) |
| **Examines & Info** | Combine messages like item/npc/object examine, welcome/info tips |
| **Game Messages** | Combine common game messages like skill actions or feedback |
| **Minimum Repeat Count** | How many identical messages must appear before collapsing |
| **Reset After (ms)** | Time in milliseconds after which the repeat tracking resets |

<img width="235" height="210" alt="image" src="https://github.com/user-attachments/assets/2bfe49ab-2a1e-4bfc-b780-310efa784639" />


---

## 💬 Example Output

Given `Minimum Repeat Count = 4`:

```text
You clean the Grimy guam leaf
You clean the Grimy guam leaf
You clean the Grimy guam leaf
(4x) You clean the Grimy guam leaf
```

Instead of having 27 lines of spam for a single inventory of herbs, now (based on the plugin configuration) you can actually have time to understand what is going on!

<img width="219" height="64" alt="image" src="https://github.com/user-attachments/assets/cf3d96ff-cf1a-44ce-860c-608d93c08a86" />
