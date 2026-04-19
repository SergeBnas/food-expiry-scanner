# ShelfLife – Food Expiry Scanner

## Overview

ShelfLife is a mobile application that helps users manage food items in their fridge or pantry.
It allows users to scan products, track expiry dates, and reduce food waste through insights and meal suggestions.

\---

## Features

### Inventory Management

* Add food items manually or via barcode
* Track quantity and expiry dates
* View all items in the Library

### Barcode Scanning

* Scan products using the camera
* Automatically fetch product details using Open Food Facts API

### Sync System

* Sync local data with backend (Node.js + Express)
* Works offline using Room database

### Expiry Tracking

* View items expiring soon
* Get reminders before expiry

### Meal Suggestions

* Suggest meals based on expiring ingredients

### Insights

* Track food usage and waste patterns

\---

## Tech Stack

### Mobile App

* Kotlin (Android)
* Room Database
* RecyclerView + Material Design

### Backend

* Node.js
* Express.js

### API

* Open Food Facts

\---

## Setup

### Run Backend

cd backend
npm install
npm start

### Run App

* Open in Android Studio
* Run on emulator or device

\---

## Notes

* Backend must be running for Sync
* Emulator uses: http://10.0.2.2:5000

\---

## Test Barcode

3017620422003 (Nutella)

\---

## Team

* Serge Bou Nassar
* Mathieu Moussa
* Gaelle Achkouty
* Yara Chaaya

\---

## Goal

Reduce food waste through better tracking and smart suggestions.

