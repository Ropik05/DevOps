from flask import Flask
import psycopg2

app = Flask(__name__)

def get_users():
    conn = psycopg2.connect(
        host="db",
        database="users_db",
        user="postgres",
        password="postgres"
    )
    cur = conn.cursor()
    cur.execute("SELECT name FROM users;")
    users = [row[0] for row in cur.fetchall()]
    cur.close()
    conn.close()
    return users

@app.route('/')
def hello():
    users = get_users()
    users_list = "<br>".join(f"- {u}" for u in users)
    return f"""
    👋 Hello from Flask + PostgreSQL!<br>
    Current users in DB:<br>{users_list}
    """

if __name__ == "__main__":
    app.run(host="0.0.0.0", port=5000)
