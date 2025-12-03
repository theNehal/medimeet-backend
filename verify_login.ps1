$baseUrl = "http://localhost:8080"

Write-Host "1. Testing Login with Valid Credentials..."
$body = @{
    email = "test@example.com"
    password = "password"
} | ConvertTo-Json

$response = Invoke-RestMethod -Uri "$baseUrl/auth/login" -Method Post -Body $body -ContentType "application/json" -ErrorAction Stop
Write-Host "Login Successful!"
Write-Host "Token: $($response.token)"
$token = $response.token

Write-Host "`n2. Testing Login with Invalid Credentials..."
$bodyInvalid = @{
    email = "test@example.com"
    password = "wrongpassword"
} | ConvertTo-Json

try {
    Invoke-RestMethod -Uri "$baseUrl/auth/login" -Method Post -Body $bodyInvalid -ContentType "application/json"
    Write-Host "Error: Login should have failed!" -ForegroundColor Red
} catch {
    Write-Host "Login Failed as expected. Status: $($_.Exception.Response.StatusCode.value__)"
}

Write-Host "`n3. Testing Protected Endpoint with Token..."
$headers = @{
    Authorization = "Bearer $token"
}

try {
    $protectedResponse = Invoke-RestMethod -Uri "$baseUrl/auth/test" -Method Get -Headers $headers
    Write-Host "Protected Endpoint Response: $protectedResponse"
} catch {
    Write-Host "Failed to access protected endpoint: $($_.Exception.Message)" -ForegroundColor Red
}
