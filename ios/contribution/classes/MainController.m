//
//  MainController.m
//  contribution
//
//  Created by 352 on 16/1/7.
//  Copyright © 2016年 352. All rights reserved.
//

#import "MainController.h"

@interface MainController ()

@end

@implementation MainController

@synthesize token;

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view, typically from a nib.
    self.label.text = token;
}

@end