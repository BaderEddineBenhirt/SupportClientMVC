/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    './src/main/resources/templates/**/*.html', // This path should point to your HTML files
    './src/main/resources/static/js/**/*.js', // This path should point to your JS files if they contain any Tailwind CSS classes
  ],
  theme: {
    extend: {},
  },
  plugins: [],
}

