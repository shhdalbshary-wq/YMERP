UserSession session = new UserSession(DashboardActivity.this);

String username   = session.getUsername();
String fiscalYear = session.getFiscalYear();
String subscriber = session.getSubscriber();
String unit       = session.getUnit();
String branch     = session.getBranch;

// عرض البيانات في البطاقة
tvUserName.setText(username);
tvFiscalYear.setText("السنة المالية: " + fiscalYear);
