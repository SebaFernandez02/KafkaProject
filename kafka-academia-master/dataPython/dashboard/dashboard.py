import time

import pandas as pd
import plotly.express as px
import dash
from dash import dcc, html, Input, Output

# Ruta del archivo CSV
CSV_FILE_PATH = "../output/reporte_areas.csv"


# Cargar y procesar datos
def load_data():
    try:
        data = pd.read_csv(CSV_FILE_PATH, header=None, skip_blank_lines=True)

        # Detectar las secciones del archivo
        area_data = data.iloc[:data[data[0] == "Total de Suscriptores"].index[0], :].copy()
        area_data.columns = ["Área del Parque", "Cantidad de Usuarios"]
        area_data = area_data[1:]  # Saltar encabezados

        total_index = data[data[0] == "Total de Suscriptores"].index[0]
        total_data = data.iloc[total_index:total_index + 2, :].copy()

        date_index = data[data[0] == "Fecha"].index[0]
        date_data = data.iloc[date_index + 1 :, :].copy()
        date_data.columns = ["Fecha", "Cantidad de Usuarios"]

        # Convertir las columnas al tipo correcto
        area_data["Cantidad de Usuarios"] = pd.to_numeric(area_data["Cantidad de Usuarios"])
        date_data["Fecha"] = pd.to_datetime(date_data["Fecha"]).dt.date  # Convertir a solo día
        date_data["Cantidad de Usuarios"] = pd.to_numeric(date_data["Cantidad de Usuarios"])

        # Agrupar por fecha y sumar usuarios
        date_data = date_data.groupby("Fecha", as_index=False).sum()

        # Extraer el total de suscriptores
        total_suscribers = total_data.iloc[0, 1]  # Primer valor de la fila "Total de Suscriptores"

        return area_data, total_data, date_data, total_suscribers
    except Exception as e:
        print(f"Error al cargar datos: {e}")
        return pd.DataFrame(), pd.DataFrame(), pd.DataFrame(), 0


# Crear la aplicación Dash
app = dash.Dash(__name__)

app.layout = html.Div([
    html.H1("Dashboard - Parque Sebalandia"),

    # Muestra el total de suscriptores en la parte superior
    html.Div([
        html.H3("Total de Usuarios Registrados: ", style={'display': 'inline-block'}),
        html.H3(id="total-users", style={'color': 'blue', 'display': 'inline-block'})
    ], style={'textAlign': 'center', 'marginBottom': '20px'}),

    # Gráfico de barras por área
    dcc.Graph(id="bar-chart"),

    # Gráfico de línea por fecha
    dcc.Graph(id="line-chart"),
])


@app.callback(
    Output("total-users", "children"),
    Input("bar-chart", "id")
)
def update_total_users(_):
    _, _, _, total_suscribers = load_data()
    return total_suscribers


@app.callback(
    Output("bar-chart", "figure"),
    Input("bar-chart", "id")
)
def update_bar_chart(_):
    area_data, _, _, _ = load_data()
    if area_data.empty:
        return px.bar(title="No hay datos disponibles")
    return px.bar(area_data, x="Área del Parque", y="Cantidad de Usuarios", title="Usuarios por Área del Parque")


@app.callback(
    Output("line-chart", "figure"),
    Input("line-chart", "id")
)
def update_line_chart(_):
    _, _, date_data, _ = load_data()
    if date_data.empty:
        return px.line(title="No hay datos disponibles")

    fig = px.line(date_data, x="Fecha", y="Cantidad de Usuarios", title="Usuarios por Fecha", markers=True)

    # Ajustar el formato de la fecha en la línea X
    fig.update_layout(
        xaxis=dict(
            tickformat="%Y-%m-%d"  # Formato de solo fecha (sin hora)
        )
    )

    return fig


if __name__ == "__main__":
    app.run_server(debug=True, host="localhost", port=8050)
