# 📈 臺灣證券查詢 App（Android工程師實作考題 C卷）

以 Kotlin 搭配 Jetpack Compose 開發的 Android App，用來查詢臺灣證券交易所個股行情資訊，包含本益比、殖利率、成交資訊與月平均價等。

> 📘 依照 C卷考題需求開發，採用 MVVM 架構設計，搭配 Material Design 3。

---

## 🔧 技術說明

| 技術項目     | 說明                            |
|--------------|---------------------------------|
| 🧠 架構模式 | MVVM（ViewModel + StateFlow）     |
| 🎨 UI技術   | Jetpack Compose + Material3       |
| 📦 資料來源 | 臺灣證交交易所 Open API           |
| 🌗 主題切換 | 支援 Light / Dark Mode            |
| 📱 螢幕支援 | 支援螢幕旋轉與 UI狀態保存         |
| 🌿 色系     | 以類似國泰綠作為App主色           |

---

## ✅ 已完成功能

- 🔍 使用三組 API 合併顯示股票資訊：
  - `/BWIBBU_ALL`: 本益比、殖利率、股價淨值比
  - `/STOCK_DAY_AVG_ALL`: 收盤價、月平均價
  - `/STOCK_DAY_ALL`: 成交資訊

- 📊 主要畫面：
  - 股票代號與名稱標示
  - 漲跌價差（紅漲綠跌）
  - 收盤價 vs 月平均價（紅高綠低）
  - 成交筆數、金額、股數
  - 點擊卡片展開詳細資訊（含本益比等）

- 🧾 排序功能：
  - 支援依「股票代號」、「收盤價」、「漲跌」、「殖利率」排序
  - 點擊排序欄位切換：降序 → 升序 → 取消排序
  - 顯示對應箭頭 icon 以標示當前排序狀態

- 🌓 Dark Mode 支援：
  - 使用 `rememberSaveable` 保存深色狀態
  - 同步控制 Status Bar icon 顏色與背景

- 📱 螢幕旋轉狀態保留：
  - 透過 `rememberSaveable` 保持主題與排序狀態
  - 避免重新整理資料與畫面閃爍

---

## 🌟 加分項完成

- ✅ 使用 Jetpack Compose 撰寫 UI
- ✅ 採用 Material3 Design Style
- ✅ 系統狀態列顏色依主題變化（使用 Accompanist）
- ✅ 深色模式切換
- ✅ 排序箭頭與項目高亮提示
- ✅ UI 響應式設計、支援卡片滑動動畫、螢幕轉向與Dark Mode
- ✅ 使用類似國泰綠為App風格色調

---

## 📎 展示影片（依考題需求，請設為「非公開」）

🔗 [👉 點我觀看操作影片](https://www.youtube.com/watch?v=PzrPj08Gq10)（再煩請考官協助觀看！）

---

## 🧩 專案版本資訊

| 類別             | 設定內容                           |
|------------------|------------------------------------|
| 📦 Android SDK   | `compileSdk = 34`, `targetSdk = 34`, `minSdk = 24` |
| 🧱 Build Tool    | Gradle `8.11.1`             |
| 🛠 Kotlin 版本   | `1.8`（JVM Target）               |
| 🎨 Compose 版本  | `1.5.4` + Material 3 `1.2.1`        |
| 🌿 系統欄套件    | Accompanist `0.33.2-alpha`         |
| 🔌 主要依賴      | Retrofit + Coroutine + Jetpack Compose + ViewModel |

---

## 🧑‍💻 作者

開發者：陳品維

Email：adidas564421@gmail.com