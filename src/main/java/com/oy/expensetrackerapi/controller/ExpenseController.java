package com.oy.expensetrackerapi.controller;

import com.oy.expensetrackerapi.entity.Expense;
import com.oy.expensetrackerapi.entity.ValueObject;
import com.oy.expensetrackerapi.entity.MassAssignment;
import com.oy.expensetrackerapi.repository.ExpenseRepository;
import com.oy.expensetrackerapi.service.ExpenseService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;


@RestController()
@RequestMapping("/v1")
@Api(tags = "Expense API", description = "Expense CRUD Operations")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @Autowired
    private ExpenseRepository expenseRepository;

    @GetMapping("/expenses")
    public List<Expense> getAllExpenses(Pageable pageable){
        return expenseService.getAllExpenses(pageable).toList();
    }


    @GetMapping("/expenses/{id}")
    public Expense getExpenseById(@PathVariable("id") Long id) {
        return expenseService.getExpenseById(id);
    }

    @DeleteMapping("/expenses")
    public void deleteExpenseById(@RequestParam("id") Long id){
        expenseService.deleteExpenseById(id);
    }

    @PostMapping("/expenses")
    public Expense saveExpenseDetails(@Valid @RequestBody Expense expense){
        return expenseService.saveExpenseDetails(expense);
    }

    @PutMapping("/expenses/{id}")
    public Expense updateExpenseDetails(@RequestBody Expense expense, @PathVariable Long id) {
        return expenseService.updateExpenseDetails(id, expense);
    }


    @GetMapping("/expenses/name")
    public List<Expense> getAllExpensesByName(@RequestParam String name, Pageable pageable) {
        return expenseService.readByName(name, pageable);
    }

    @GetMapping("/expenses/category")
    public List<Expense> getExpensesByCategory(@RequestParam String category, Pageable pageable) {
        return expenseService.readByCategory(category, pageable);
    }

    @GetMapping("/expenses/image")
    public List<Expense> getExpenseByIdSql(@RequestParam String category) {
        System.out.println("SQL query called");
        return expenseRepository.getExpenseByIdSql(category);
    }

    @GetMapping("/expenses/date")
    public List<Expense> getAllExpensesByDate(
            @RequestParam(required = false)@DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam(required = false)@DateTimeFormat(pattern = "yyyy-MM-dd")Date endDate,
            Pageable pageable
            )                {
        return expenseService.readByDate(startDate, endDate, pageable);
    }

    @GetMapping("/getXFrameOptions")
    public ResponseEntity getXFrameOptions(){
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Frame-Options", "DENY");
        return new ResponseEntity(headers,HttpStatus.OK);
    }

    @GetMapping("/getStrictTransportSecurity")
    public ResponseEntity getStrictTransportSecurity(){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Strict-Transport-Security", "max-age=63072000; includeSubDomains; preload");
        return new ResponseEntity(headers,HttpStatus.OK);
    }

    @GetMapping("/getContentType")
    public ResponseEntity getContentType(){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity(headers,HttpStatus.OK);
    }

    @GetMapping("/getContentSecurityPolicy")
    public ResponseEntity getContentSecurityPolicy(){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Security-Policy", "frame-ancestors 'none';");
        return new ResponseEntity(headers,HttpStatus.OK);
    }

    @GetMapping("/getAccessControlAllowOrigin")
    public ResponseEntity getAccessControlAllowOrigin(@RequestHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN) String value){

        if(value == null || value.equalsIgnoreCase("null")) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/getXContentTypeOptions")
    public ResponseEntity getXContentTypeOptions(){
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Content-Type-Options", "nosniff");
        return new ResponseEntity(headers,HttpStatus.OK);
    }

    @GetMapping("/getPubliclyAccessibleLeakyAPIs")
    public ResponseEntity getPubliclyAccessibleLeakyAPIs(){
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/getPubliclyAccessibleByDesign")
    public ResponseEntity getPubliclyAccessibleByDesign(){
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/getAzureAPIWithoutRateLimiting")
    public ResponseEntity getAzureAPIWithoutRateLimiting(){
        HttpHeaders headers = new HttpHeaders();
        headers.add("x-ms-ratelimit-remaining-resource", "Microsoft.Compute/DeleteVMScaleSet3Min;107");
        return new ResponseEntity(headers,HttpStatus.OK);
    }
    @GetMapping("/getXAspNetVersion")
    public ResponseEntity getXAspNetVersion(){
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Asp-Net-Version", "2.1");
        return new ResponseEntity(headers,HttpStatus.OK);
    }

    @GetMapping("/getXPoweredBy")
    public ResponseEntity getXPoweredBy(){
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Powered-By", "2.1");
        return new ResponseEntity(headers,HttpStatus.OK);
    }

    @PostMapping("/testHTTPMethods")
    public ResponseEntity testHTTPMethodsPost(){
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/testHTTPMethods")
    public ResponseEntity testHTTPMethodsGet(){
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/testHTTPMethods")
    public ResponseEntity testHTTPMethodsDelete(){
        return new ResponseEntity(HttpStatus.OK);
    }


    @PutMapping("/testHTTPMethods")
    public ResponseEntity testHTTPMethodsPut(){
        return new ResponseEntity(HttpStatus.OK);
    }


    @GetMapping("/unauthenticatedCallingAPI")
    public ResponseEntity unauthenticatedCallingAPI(){
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/getResponseTime")
    public ResponseEntity getResponseTime() throws InterruptedException {
        Thread.sleep(TimeUnit.SECONDS.toMillis(35));
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/checkContainsSensitiveDataIPAddress")
    public ResponseEntity checkContainsSensitiveDataIPAddress() {
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/checkContainsSensitiveDataTCKN")
    public ResponseEntity checkContainsSensitiveDataTCKN() {
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/checkContainsSensitiveDataSSN")
    public ResponseEntity checkContainsSensitiveDataSSN() {
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/checkContainsSensitiveDataEmail")
    public ResponseEntity checkContainsSensitiveDataEmail() {
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/checkContainsSensitiveDataGoogleAPIKey")
    public ResponseEntity checkContainsSensitiveDataGoogleAPIKey() {
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/checkContainsSensitiveDataCreditCard")
    public ResponseEntity checkContainsSensitiveDataCreditCard() {
        return new ResponseEntity(HttpStatus.OK);
    }



    @GetMapping("/checkServerSideRequestForgery")
    public ResponseEntity checkServerSideRequestForgery() {
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/checkSequentialIDsAttack")
    public ResponseEntity checkSequentialIDsAttack() {
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/massiveDataOnResponse")
    public ResponseEntity massiveDataOnResponse() {
        byte[] result = new byte[4000000];
        return ResponseEntity.ok(result);
    }

    @GetMapping("/nullValueAcception")
    public ResponseEntity nullValueAcception() {
        return new ResponseEntity(HttpStatus.OK);
    }
    @GetMapping("/JWTSensitiveDataDisclosure")
    public ResponseEntity JWTSensitiveDataDisclosure() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("isAdmin", "true");
        headers.add("password", "1234");
        headers.add("name", "name");
        return new ResponseEntity(headers,HttpStatus.OK);
    }

    @GetMapping("/sendingJWTInURLParameters")
    public ResponseEntity sendingJWTInURLParameters() {
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/massAssignment")
    public MassAssignment massAssignment(@RequestBody MassAssignment request) {
        return MassAssignment.builder().name("ali").surname("gel").email("ali@gmail.com").role("admin").build();
    }

    @GetMapping("/massAssignmentChangedData")
    public MassAssignment massAssignmentChangedData(@RequestBody MassAssignment request) {
        return MassAssignment.builder().name(request.getName()).surname(request.getSurname()).email(request.getEmail()).role("roleChanged").build();
    }

    @GetMapping("/improperInputValidation")
    public ResponseEntity improperInputValidation(@RequestBody ValueObject request) {
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/sensitiveInformationDisclosureTCKN")
    public ValueObject sensitiveInformationDisclosureTCKN() {
        return ValueObject.builder().value("76045903576").build();
    }

    @GetMapping("/sensitiveInformationDisclosureSSN")
    public ValueObject sensitiveInformationDisclosureSSN() {
        return ValueObject.builder().value("540-71-1234").build();
    }

    @GetMapping("/sensitiveInformationDisclosureCreditCard")
    public ValueObject sensitiveInformationDisclosureCreditCard() {
        return ValueObject.builder().value("1392466526007745").build();
    }

    @GetMapping("/sensitiveInformationDisclosureEMail")
    public ValueObject sensitiveInformationDisclosureEMail() {
        return ValueObject.builder().value("sample@gmail.com").build();
    }

    @GetMapping("/sensitiveInformationDisclosureGoogleAPIKey")
    public ValueObject sensitiveInformationDisclosureGoogleAPIKey() {
        return ValueObject.builder().value("AIzaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa").build();
    }

    @GetMapping("/sensitiveInformationDisclosureFullPathDisclosure")
    public ValueObject sensitiveInformationDisclosureGoogleFullPathDisclosure() {
        return ValueObject.builder().value("myfile.txt").build();
    }

    @GetMapping("/improperAssetsManagementVersionEndpoint")
    public ResponseEntity improperAssetsManagementVersion() {
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/improperAssetsManagementVersionQueryString")
    public ResponseEntity improperAssetsManagementQueryString(@RequestParam("v") String version) {
        if(version.equalsIgnoreCase("3")) {
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/improperAssetsManagementVersionHeader")
    public ResponseEntity improperAssetsManagementVersionHeader(@RequestHeader("v") String version) {
        if(version.equalsIgnoreCase("3")) {
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/byPassXFFHeader")
    public ResponseEntity byPassXFFHeader(@RequestHeader(value = "X-Originating-IP", required = false) String value) {
        if(value != null) {
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/cors")
    public ResponseEntity cors(){
        return new ResponseEntity(HttpStatus.OK);
    }



}
