//
//  ViewController.m
//  test2
//
//  Created by 352 on 15/12/29.
//  Copyright © 2015年 352. All rights reserved.
//

#import "ViewController.h"
#import "ASIHTTPRequest.h"
#import "ASIFormDataRequest.h"

@interface ViewController ()

@end

@implementation ViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view, typically from a nib.
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (IBAction)login:(id)sender {
    NSString *name = self.name_txt.text;
    NSString *password = self.password_txt.text;
    NSURL *url = [NSURL URLWithString:@"http://192.168.9.99/login"];
    ASIFormDataRequest *request = [ASIFormDataRequest requestWithURL:url];
    [request setPostValue:(name) forKey:(@"name")];
    [request setPostValue:(password) forKey:(@"password")];
    [request setDelegate:self];
    [request startAsynchronous];
}

- (IBAction)reg:(id)sender {
    NSString *name = self.name_txt.text;
    NSString *password = self.password_txt.text;
    NSURL *url = [NSURL URLWithString:@"http://192.168.9.99/reg"];
    ASIFormDataRequest *request = [ASIFormDataRequest requestWithURL:url];
    [request setPostValue:(name) forKey:(@"name")];
    [request setPostValue:(password) forKey:(@"password")];
    [request setDelegate:self];
    [request startAsynchronous];
}

- (void)requestFinished:(ASIHTTPRequest *)request
{
    if ([[request.url absoluteString] isEqualToString: @"http://192.168.9.99/login"]) {
        NSLog(@"login response");
    } else if ([[request.url absoluteString] isEqualToString: @"http://192.168.9.99/reg"]) {
        NSLog(@"reg response");
    }
    // Use when fetching text data
    NSString *responseString = [request responseString];
    
    // Use when fetching binary data
    //NSData *responseData = [request responseData];
    NSLog(@"%@", responseString);
    
    
    
    NSData *data = [responseString dataUsingEncoding:NSUTF8StringEncoding];
    NSError *error = nil;
    NSDictionary *json = [NSJSONSerialization JSONObjectWithData:data options:NSJSONReadingMutableLeaves error:&error];
    
    if (error != NULL) {
        NSLog(@"error:%@", error.description);
    } else if (json) {
        NSLog(@"login status = %@", [json valueForKey:@"status"]);
    }
    //id jsonObject = [NSJSONSerialization JSONObjectWithData:data options:NSJSONReadingAllowFragments error:&error];
    //if ([jsonObject isKindOfClass:[NSDictionary class]]){
    //    NSDictionary *result = (NSDictionary *)jsonObject;
    //    NSLog(@"Dersialized JSON Dictionary = %@", [result valueForKey:@"status"]);
    //} else if ([jsonObject isKindOfClass:[NSArray class]]){
    //    NSArray *nsArray = (NSArray *)jsonObject;
    //}
}

- (void)requestFailed:(ASIHTTPRequest *)request
{
    NSError *error = [request error];
    NSLog(@"error%ld", error.code);
}

@end
