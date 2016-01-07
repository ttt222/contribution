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
#import "JsonUtil.h"
#import "MainController.h"

@interface ViewController ()

@property NSString* _token;

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
    NSURL *url = [NSURL URLWithString:@"http://192.168.9.138:2016/api/accounts/login"];
    ASIFormDataRequest *request = [ASIFormDataRequest requestWithURL:url];
    NSDictionary *dictionary = [[NSMutableDictionary alloc] init];
    [dictionary setValue:name forKey:@"mobile"];
    [dictionary setValue:password forKey:@"password"];
    //[request setPostValue:(name) forKey:(@"mobile")];
    //[request setPostValue:(password) forKey:(@"password")];
    NSString *data = [JsonUtil DictionaryToJsonString:dictionary];
    NSLog(@"%@", data);
    [request addRequestHeader:@"Content-Type" value:@"application/json"];
    [request setPostBody:[JsonUtil DictionaryToMutableData:dictionary]];
    [request setDelegate:self];
    [request startAsynchronous];
}

- (IBAction)reg:(id)sender {
    NSString *name = self.name_txt.text;
    NSString *password = self.password_txt.text;
    NSURL *url = [NSURL URLWithString:@"http://192.168.9.138:2016/api/accounts/register"];
    ASIFormDataRequest *request = [ASIFormDataRequest requestWithURL:url];
    [request setPostValue:(name) forKey:(@"name")];
    [request setPostValue:(password) forKey:(@"password")];
    [request setDelegate:self];
    [request startAsynchronous];
}

- (void)requestFinished:(ASIHTTPRequest *)request {
    if ([[request.url absoluteString] isEqualToString: @"http://192.168.9.138:2016/api/accounts/login"]) {
        NSLog(@"login response");
    } else if ([[request.url absoluteString] isEqualToString: @"http://192.168.9.138:2016/api/accounts/register"]) {
        NSLog(@"reg response");
    }
    // Use when fetching text data
    NSString *responseString = [request responseString];
    NSString *msg = [request responseStatusMessage];
    int code = [request responseStatusCode];
    NSLog(@"%i,%@,%@", code, responseString, msg);
    if (code == 200) {
        NSDictionary* result = [JsonUtil JsonStringToDictionary:responseString];
        if (result) {
            self._token = [result valueForKey:@"token"];
            //[self alert:token];
            [self performSegueWithIdentifier:@"login" sender:self];
        }
    } else {
        NSDictionary* result = [JsonUtil JsonStringToDictionary:responseString];
        if (result)
            [self alert:[result valueForKey:@"message"]];
    }
}

- (void)requestFailed:(ASIHTTPRequest *)request {
    NSError *error = [request error];
    NSLog(@"error%i", error.code);
}

- (void)alert:(NSString *) msg {
    UIAlertController *alertController = [UIAlertController alertControllerWithTitle:@"提示" message:msg preferredStyle:UIAlertControllerStyleAlert];
    UIAlertAction *okAction = [UIAlertAction actionWithTitle:@"OK" style:UIAlertActionStyleDefault handler:^(UIAlertAction *action) {}];
    [alertController addAction:okAction];
    [self presentViewController:alertController animated:YES completion:nil];
}

- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
    NSString* name = segue.identifier;
    if ([name isEqualToString:@"login"]) {
        UIViewController* page = segue.destinationViewController;
        if ([page respondsToSelector:@selector(setToken:)]) {
            [page setValue:self._token forKey:@"token"];
        }
    }
}

@end
