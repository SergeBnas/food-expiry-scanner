# Food Expiry Scanner Backend

This backend uses **Node.js + Express** and provides the REST API used by the Android app.

## Endpoints
- `GET /api/items`
- `POST /api/items`
- `PUT /api/items/:id`
- `DELETE /api/items/:id`

## Run it

```bash
npm install
npm start
```

The server runs on `http://localhost:5000`.

For the Android emulator, the app calls `http://10.0.2.2:5000/`.
