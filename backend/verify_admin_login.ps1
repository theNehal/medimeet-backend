$baseUrl = "http://localhost:8080"

Write-Host "Testing Admin Login..."
$body = @{
    email = "admin@example.com"
    password = "password"
} | ConvertTo-Json

try {
    $response = Invoke-RestMethod -Uri "$baseUrl/auth/login" -Method Post -Body $body -ContentType "application/json" -ErrorAction Stop
    Write-Host "Admin Login Successful!"
    Write-Host "Token: $($response.token)"
    Write-Host "Roles: $($response.roles)"
} catch {
    Write-Host "Admin Login Failed: $($_.Exception.Message)" -ForegroundColor Red
    Write-Host "Status Code: $($_.Exception.Response.StatusCode.value__)"
}
