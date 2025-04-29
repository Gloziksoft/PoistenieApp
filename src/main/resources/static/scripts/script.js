// Initialize TomSelect for policy holder selection
new TomSelect("#policyHolderId", {
    create: false, // disable creation of new options
    sortField: { field: "text", direction: "asc" } // sort options alphabetically
});

// Initialize TomSelect for insured person selection
new TomSelect("#insuredPersonId", {
    create: false,
    sortField: { field: "text", direction: "asc" }
});

// Initialize TomSelect for insurance type selection
new TomSelect("#insuranceType", {
    create: false,
    sortField: { field: "text", direction: "asc" }
});
