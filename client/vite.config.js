import { defineConfig } from "vite";
import react from "@vitejs/plugin-react";

// https://vitejs.dev/config/

export default defineConfig(
  {
    plugins: [react()],
    define: {
      global: {},
    },
    server: {
      proxy: {
        "/api": {
          target: "http://139.59.155.108:8080",
          changeOrigin: true,
          secure: false,
          ws: true,
        },
      },
    },
  }
  // some other configuration
);
